package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.model.Check;
import com.cashRegister.model.Shift;
import com.cashRegister.model.User;
import com.cashRegister.repository.CheckRepository;
import com.cashRegister.repository.ShiftRepository;
import com.cashRegister.repository.UserRepository;
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

@WebServlet(WebAdresses.ADMIN_TODAY_CHECKS)
public class AdminTodayChecksServlet extends HttpServlet {

    private final UserRepository userRepository;
    private final CheckRepository checkRepository;
    private final ShiftRepository shiftRepository;

    private static final Logger log = LogManager.getLogger(AdminTodayChecksServlet.class);

    public AdminTodayChecksServlet() {
        userRepository = UserRepository.getUserRepository();
        checkRepository = CheckRepository.getCheckRepository();
        shiftRepository = ShiftRepository.getShiftRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (shiftRepository.firstOpenShift() == null) {
            resp.sendRedirect(WebAdresses.NO_OPEN_REPORT);
        } else {
            Shift openShift = shiftRepository.firstOpenShift();
            if (req.getSession().getAttribute("openShift") != null) {
                req.getSession().removeAttribute("openShift");
            }
            req.getSession().setAttribute("openShift", openShift);

            List<User> usersList = userRepository.getAllUsers();
            Map<User, List<Check>> mapNotReturned = new HashMap<>();
            Map<User, List<Check>> mapReturned = new HashMap<>();

            Double sumNotReturned = 0.0;
            Double sumReturned = 0.0;

            for (User user : usersList) {
                if (user.getRoleName().getName().equals("Casher")) {
                    List<Check> currentCasherChecksNotReturned = checkRepository.getAllChecksCurrentCasherOpenShift(user).
                            stream().filter(x -> !x.isReturned()).collect(Collectors.toList());
                    List<Check> currentCasherChecksReturned = checkRepository.getAllChecksCurrentCasherOpenShift(user).
                            stream().filter(x -> x.isReturned()).collect(Collectors.toList());

                    if (currentCasherChecksNotReturned != null && !currentCasherChecksNotReturned.isEmpty()) {
                        sumNotReturned += currentCasherChecksNotReturned.stream().map(x -> x.getSum()).reduce(Double::sum).get();
                        mapNotReturned.put(user, currentCasherChecksNotReturned);

                    }
                    if (currentCasherChecksReturned != null && !currentCasherChecksReturned.isEmpty()) {
                        sumReturned += currentCasherChecksReturned.stream().map(x -> x.getSum()).reduce(Double::sum).get();
                        mapReturned.put(user, currentCasherChecksReturned);
                    }

                }
            }

            if (mapNotReturned != null) {
                req.getSession().setAttribute("mapNotReturned", mapNotReturned);
                req.getSession().setAttribute("sumNotReturned", sumNotReturned);
            }
            if (mapReturned != null) {
                req.getSession().setAttribute("mapReturned", mapReturned);
                req.getSession().setAttribute("sumReturned", sumReturned);
            }

            resp.sendRedirect(WebAdresses.X_REPORT);
        }
    }

}
