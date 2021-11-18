package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.exception.ShiftNotFoundException;
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

    private ShiftRepository shiftRepository;
    private CheckRepository checkRepository;

    private static final Logger log = LogManager.getLogger(CasherStartServlet.class);

    public CasherStartServlet() {
        this.shiftRepository = ShiftRepository.getShiftRepository();
        this.checkRepository = CheckRepository.getCheckRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("come to do get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate localDate = LocalDate.now();
        Shift openShift = ShiftRepository.getShiftRepository().firstOpenShift();
        req.getSession().setAttribute("openshift", openShift);
        req.getSession().setAttribute("localDate", localDate);

        User user = (User)req.getSession().getAttribute("user");
        List<Check> checkList = checkRepository.getAllChecksCurrentCasherOpenShift(user);
        req.getSession().setAttribute("checksOfCasher", checkList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CASHER_START_FORWARD);
        requestDispatcher.forward(req, resp);
    }
}
