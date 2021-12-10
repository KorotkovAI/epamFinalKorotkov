package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.exception.ShiftNotFoundException;
import com.cashRegister.model.Check;
import com.cashRegister.model.Shift;
import com.cashRegister.model.User;
import com.cashRegister.repository.CheckRepository;
import com.cashRegister.repository.ShiftRepository;
import com.cashRegister.repository.UserRepository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(WebAdresses.ADMIN_TODAY_CLOSE)
public class AdminTodayCloseServlet extends HttpServlet {

    private final UserRepository userRepository;
    private final CheckRepository checkRepository;
    private final ShiftRepository shiftRepository;

    private static final Logger log = LogManager.getLogger(AdminTodayCloseServlet.class);

    public AdminTodayCloseServlet() {
        this.userRepository = UserRepository.getUserRepository();
        this.checkRepository = CheckRepository.getCheckRepository();
        this.shiftRepository = ShiftRepository.getShiftRepository();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Shift openShift = shiftRepository.firstOpenShift();

        if (openShift == null) {
            log.log(Level.ERROR, "There is no open report" + AdminTodayCloseServlet.class.getName());
            resp.sendRedirect(WebAdresses.NO_OPEN_REPORT);
        } else {
            List<User> usersList = userRepository.getAllUsers();
            Map<User, List<Check>> mapNotReturned = new HashMap<>();
            Map<User, List<Check>> mapReturned = new HashMap<>();

            double sumNotReturned = 0.0;
            double sumReturned = 0.0;
            for (User user : usersList) {
                if (user.getRoleName().getName().equals("Casher")) {
                    List<Check> currentCasherChecksNotReturned = checkRepository.getAllChecksCurrentCasherOpenShift(user).
                            stream().filter(x -> !x.isReturned()).collect(Collectors.toList());
                    List<Check> currentCasherChecksReturned = checkRepository.getAllChecksCurrentCasherOpenShift(user).
                            stream().filter(Check::isReturned).collect(Collectors.toList());

                    if (!currentCasherChecksNotReturned.isEmpty()) {
                        sumNotReturned += currentCasherChecksNotReturned.stream().map(Check::getSum).reduce(Double::sum).get();
                        mapNotReturned.put(user, currentCasherChecksNotReturned);

                    }
                    if (!currentCasherChecksReturned.isEmpty()) {
                        sumReturned += currentCasherChecksReturned.stream().map(Check::getSum).reduce(Double::sum).get();
                        mapReturned.put(user, currentCasherChecksReturned);
                    }

                }
            }

            if (!mapNotReturned.isEmpty()) {
                req.getSession().setAttribute("mapNotReturned", mapNotReturned);
                req.getSession().setAttribute("sumNotReturned", sumNotReturned);
            }
            if (!mapReturned.isEmpty()) {
                req.getSession().setAttribute("mapReturned", mapReturned);
                req.getSession().setAttribute("sumReturned", sumReturned);
            }

            boolean status = false;

            try {
                status = shiftRepository.closeShift(openShift.getId());
            } catch (IllegalAccessException | ShiftNotFoundException e) {
                log.log(Level.ERROR, e.getMessage() + AdminTodayCloseServlet.class.getName());
            }

            if (status) {
                try {
                    Shift closeShift = shiftRepository.getShiftById(openShift.getId());
                    req.getSession().setAttribute("closeShift", closeShift);
                    req.getSession().removeAttribute("openShift");
                } catch (ShiftNotFoundException e) {
                    log.printf(Level.ERROR, "Shift not found" + e.getMessage() + AdminTodayCloseServlet.class.getName());
                }
                resp.sendRedirect(WebAdresses.Z_REPORT);
            } else {
                log.log(Level.ERROR, "Can't close shift" + AdminTodayCloseServlet.class.getName());
                resp.sendRedirect(WebAdresses.ERROR_PAGE);
            }

        }
    }
}
