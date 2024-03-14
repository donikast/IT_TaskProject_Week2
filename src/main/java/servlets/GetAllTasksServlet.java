package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.TaskRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tasks")
public class GetAllTasksServlet extends HttpServlet {
    private TaskRepository taskRepository;
    @Override
    public void init() throws ServletException {
        taskRepository = TaskRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        String tasks = taskRepository.getAllTasks();

        if(!tasks.isEmpty()) {
            pw.println("<html><body><p>");
            pw.println("Task found!");
            pw.println(tasks);
            pw.println("</p></body></html>");
            resp.setStatus(200);
        }
        else {
            pw.println("<html><body><p>");
            pw.println("No task found!");
            pw.println("</p></body></html>");
            resp.setStatus(404);
        }
        pw.close();
    }
}
