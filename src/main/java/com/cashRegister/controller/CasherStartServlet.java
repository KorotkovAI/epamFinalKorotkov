package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.model.Check;
import com.cashRegister.model.Shift;
import com.cashRegister.model.User;
import com.cashRegister.repository.CheckRepository;
import com.cashRegister.repository.ShiftRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(WebAdresses.CASHER_START_PAGE)
public class CasherStartServlet extends HttpServlet {

    private final CheckRepository checkRepository;

    private static final Logger log = LogManager.getLogger(CasherStartServlet.class);

    public CasherStartServlet() {
        checkRepository = CheckRepository.getCheckRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CASHER_START_FORWARD);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate localDate = LocalDate.now();
        Shift openShift = ShiftRepository.getShiftRepository().firstOpenShift();
        req.getSession().setAttribute("openshift", openShift);
        req.getSession().setAttribute("localDate", localDate);

        User user = (User) req.getSession().getAttribute("user");
        if (req.getSession().getAttribute("checksOfCasher") == null) {
            List<Check> checkList = checkRepository.getAllChecksCurrentCasherOpenShift(user);
            req.getSession().setAttribute("checksOfCasher", checkList);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CASHER_START_FORWARD);
        requestDispatcher.forward(req, resp);
    }
}
