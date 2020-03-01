package com.ninshu.feudWithFriends.Controllers;

import com.ninshu.feudWithFriends.Entities.AnswerList;
import com.ninshu.feudWithFriends.Entities.Question;
import com.ninshu.feudWithFriends.Entities.User;
import com.ninshu.feudWithFriends.Services.ServiceImpl.FFAnswerEngine;
import com.ninshu.feudWithFriends.Services.ServiceInterface.AnswerService;
import com.ninshu.feudWithFriends.Services.ServiceInterface.QuestionService;
import com.ninshu.feudWithFriends.Services.ServiceInterface.UserService;
import com.ninshu.feudWithFriends.Utilities.AnswerType;
import com.ninshu.feudWithFriends.model.AnswerListVO;
import com.ninshu.feudWithFriends.model.QuestionVO;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;

import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BaseController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserService userService;

    @Autowired
    FFAnswerEngine ffAnswerEngine;
    //---------------------------------Questions---------------------------------

    @GetMapping("/question/{id}")
    public Question getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/question")
    public int addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("/question")
    public QuestionVO getRandomQuestion() {
        return questionService.getRandomQuestion();
    }

    //---------------------------------Answers------------------------------------
    @GetMapping("/answer/{id}")
    public AnswerList getAnswerById(@PathVariable int id) {
        return answerService.getAnswerById(id);
    }

    @GetMapping("/answer/questionId/{questionid}")
    public List<AnswerListVO> getAnswerByQuestionId(@PathVariable int questionid) {
        return answerService.getAnswerByQuestionId(questionid);
    }

    @GetMapping("/answers")
    public List<AnswerList> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @PostMapping("/answer")
    public int addAnswer(@RequestBody AnswerList answerList) {
        return answerService.addAnswerList(answerList);
    }

    @GetMapping("/answer/{questionId}/{userAnswer}")
    public String matchAnswer(@PathVariable long questionId, @PathVariable String userAnswer) {
        return ffAnswerEngine.getAnswer(questionId, userAnswer);
    }

    //---------------------------------Users--------------------------------------

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    public User getRandomActiveUser() {
        return userService.getRandomActiveUser();
    }

    @GetMapping("/syncData")
    public Response syncDataFromXL() {
        File sourceXL = new File("/Feud-With-Friends.xlsx");
        try {
            Workbook wb = WorkbookFactory.create(sourceXL);
            Sheet sheet = wb.getSheetAt(0);
            System.out.println(sheet);

            int questionId=0;
            for(int i=1; i< sheet.getLastRowNum(); i++) {
                if(sheet.getRow(i).getCell(0) !=null && !sheet.getRow(i).getCell(0).toString().equals("")) {
                    //Todo: Add this to a question object. And whatever answers we find after this in this question
                    String questionString = sheet.getRow(i).getCell(0).getStringCellValue();
                    Question question = new Question();
                    question.setQuestion(questionString);
                    questionId = questionService.addQuestion(question);
                }
                AnswerList answer = new AnswerList();
                answer.getQuestion().setUid(questionId);
                answer.setCurrentAnswerType(AnswerType.PRIVILEGED.toString());
                answer.setOriginalAnswerType(AnswerType.PRIVILEGED.toString());
                answer.setDisplayAnswer(sheet.getRow(i).getCell(2).getStringCellValue());
                answer.setAnswerWords(sheet.getRow(i).getCell(1).getStringCellValue());
                answerService.addAnswerList(answer);

            }

        } catch (Exception e) {
            System.out.println("Error syncing data from xls file - "+ e.getStackTrace());
            return Response.serverError().build();
        }
        return Response.accepted().build();
    }

    public static void main(String[] args) {
        //JSON parser object to parse read file
        org.json.simple.parser.JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("/Users/srajolia/Downloads/Prod_DASH_Users.txt"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;

            //Iterate over employee array
            Map<String,Integer> PSUsersMap = new HashMap<String, Integer>();
            for(Object employee: employeeList) {
                JSONObject emp = (JSONObject) employee;
                PSUsersMap.put(emp.get("emailAddress").toString(), 1);
            }
            System.out.println(PSUsersMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        try {
//            FileReader fr=new FileReader("/Users/srajolia/Desktop/BidData.csv");
//            StringBuilder str = new StringBuilder();
//            int i;
//            while((i=fr.read())!=-1)
//                str.append((char)i);
//            fr.close();
//            String a = str.toString();
//            List<String> list = new ArrayList<>();
//            String[] strArr = a.split("!!!");
//            String tempBid = "";
//            List<String> finalStringArr = new ArrayList<>();
//            for (int j=0; j< strArr.length; j++) {
//                String[] cols = strArr[j].split("@@@");
//                if(tempBid.equals("")) {
////                    String res = cols[0] + "\t" + cols[1] + "\t" +cols[2] + "\n";
//                    finalStringArr.add(strArr[j]);
//                } else if(cols[0].equals(tempBid)) {
//                    String lastEditedString = finalStringArr.get(finalStringArr.size()-1);
//                    String[] lastStringArr = lastEditedString.split("@@@");
//                    String updatedLastRes = lastStringArr[0] + "@@@" + lastStringArr[1] + "," + cols[1] + "@@@" + lastStringArr[2];
//                    finalStringArr.set(finalStringArr.size()-1, updatedLastRes);
//                } else {
////                    String res2 = cols[0] + "\t" + cols[1] + "\t" +cols[2] + "\n";
//                    finalStringArr.add(strArr[j]);
//                }
//                tempBid = cols[0];
//            }
//
//            FileReader fr2=new FileReader("/Users/srajolia/Desktop/EmailDataDSV.csv");
//            StringBuilder str2 = new StringBuilder();
//            int i2;
//            while((i2=fr2.read())!=-1)
//                str2.append((char)i2);
//            fr2.close();
//            String a2 = str2.toString();
//            List<String> list2 = new ArrayList<>();
//            String[] strArr2 = a2.split("!!!");
//            String tempBid2 = "";
//            List<String> finalStringArr2 = new ArrayList<>();
//            for (int j2=0; j2< strArr2.length; j2++) {
//                finalStringArr2.add(strArr2[j2]);
//            }
//
//            //merging the two files data into one
//            //writing data to new file
//
//            File file = new File("/Users/srajolia/Desktop/samplefile1.csv");
//            FileWriter fileWriter = new FileWriter(file);
//            BufferedWriter writer = new BufferedWriter(fileWriter);
//            writer.write("User_Group_ID\tUser_Group_Name\tBIDS\tUser_Email\tFI_Name\n");
//            List<String> mergedData = new ArrayList<>();
//            for(int k=0; k<finalStringArr.size(); k++) {
//                String[] BidDataArr = finalStringArr.get(k).split("@@@");
//                Boolean didFindEmail = false;
//                for(int l=1; l<finalStringArr2.size(); l++) {
//                    String[] EmailDataArr = finalStringArr2.get(l).split("@@@");
//                    if(BidDataArr[0].equals(EmailDataArr[0])) {
//                        String mergedRow = BidDataArr[0] + "\t" + EmailDataArr[1] + "\t" + BidDataArr[1] + "\t" + EmailDataArr[2] + "\t" + BidDataArr[2] + "\n";
//
//                        writer.write(mergedRow);
//                        didFindEmail = true;
//                        break;
//                    }
//                }
//                if(!didFindEmail) {
//                    String unmergedRow = BidDataArr[0] + "\t" + null + "\t" + BidDataArr[1] + "\t" + null + "\t" + BidDataArr[2] + "\n";
//                    writer.write(unmergedRow);
//                }
//
//            }
//        }
//        catch (Exception e) {
//            System.out.println("Error syncing data from file - "+ e.getMessage());
//        }
//    }


}
