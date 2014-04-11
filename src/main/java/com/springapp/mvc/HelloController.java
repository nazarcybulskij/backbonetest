package com.springapp.mvc;

import com.google.gson.Gson;
import com.springapp.mvc.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class HelloController {
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		//return "WEB-INF/pages/asd";
        return "redirect:/pages/final.html";
	}

    @RequestMapping(value="/test",params = {"query"},method = RequestMethod.GET)
    @ResponseBody
    public List<GoogleResults.Result> print(@RequestParam(value = "query") String query) throws IOException {

        String address = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";
        //String query = "Назарко";
        String charset = "UTF-8";

        URL url = new URL(address + URLEncoder.encode(query, charset));
        Reader reader = new InputStreamReader(url.openStream(), charset);
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

        int total = results.getResponseData().getResults().size();
        System.out.println("total: "+total);

        // Show title and URL of each results
        for(int i=0; i<=total-1; i++){
            System.out.println("Title: " + results.getResponseData().getResults().get(i).getTitle());
            System.out.println("URL: " + results.getResponseData().getResults().get(i).getUrl() + "\n");

        }



       /* Map<String,String> temp=new HashMap<String,String>();
        temp.put("name","Hello");
        temp.put("id",new Integer(5).toString());  */
       return  results.getResponseData().getResults();
    }

    @RequestMapping(value = "/static", method = RequestMethod.GET)
    public String redirect() {

        return "redirect:/pages/final.html";
    }
}