package source;

import com.google.gson.*;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class AppController
{
    //get tasks older than 4 hours
    @RequestMapping("/getOlderTasks")
    @ResponseBody
    public Result getOlderTasks()
    {
        Result tasksArray = getAllTasks();       //fetch all tasks

        try
        {
            ArrayList<Task> olderTasks = new ArrayList<>();
            for (int i=0; i<tasksArray.getTasks().size(); i++)
            {
                String dateString = tasksArray.getTasks().get(i).getCREATIONDATE().substring(26, 45);    //read the date from the field
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, -4);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date minusFourHours = calendar.getTime();       //calculate the time it was 4 hours ago
                Date inputDate = sdf.parse(dateString);         //get the date and time from the input

                //compare the dates
                if (inputDate.before(minusFourHours))
                {
                    olderTasks.add(tasksArray.getTasks().get(i));
                }
            }
            return new Result(String.valueOf(olderTasks.size()), "", olderTasks);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new Result("0", "exception", new ArrayList<>());
        }
    }

    @RequestMapping("/getTasksBySecuritySystem/{securitySystem}")
    @ResponseBody
    public Result getTasksBySecuritySystem(@PathVariable("securitySystem") String securitySystem)
    {
        Result resultArray = getAllTasks();
        ArrayList<Task> tasks = new ArrayList<>();

        //filter tasks with the given security system
        for (int i=0; i<resultArray.getTasks().size(); i++)
        {
            if (resultArray.getTasks().get(i).getSECURITYSYSTEM().equals(securitySystem))
            {
                tasks.add(resultArray.getTasks().get(i));
            }
        }
        return new Result(String.valueOf(tasks.size()), "", tasks);
    }

    @RequestMapping("/getTaskByRequestId/{requestId}")
    @ResponseBody
    public Result getTaskByRequestId(@PathVariable("requestId") String requestId)
    {
        Result result = getAllTasks();

        for (int i=0; i<result.getTasks().size(); i++)
        {
            if (result.getTasks().get(i).getTASKID().equals(requestId))
            {
                return new Result(result.getTasks().get(i));
            }
        }
        return new Result("0", "no result found", new ArrayList<>());
    }


    /**
     * Fetches all the results from the input file.
     * The input file is used in order to avoid the connection to the real server which is
     * not the purpose of this exercise i suppose.
     * In the input.json place the result of "https://ibm-pprod.idaccesshub.com/ECM/ws/rest/fetchTasks
     * as it comes in postman.
     * @return Result object
     */
    private Result getAllTasks()
    {
        try
        {
            String fileName = "input.json";
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            File file = new File(classLoader.getResource(fileName).getPath().replace("%20", " "));      //.replace is needed in mac only
            String inputString = FileUtils.readFileToString(file);

            Gson gson = new Gson();
            return gson.fromJson(inputString, Result.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
