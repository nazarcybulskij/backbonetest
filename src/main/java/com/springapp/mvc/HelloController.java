package com.springapp.mvc;



import java.awt.*;
import java.io.*;
import java.net.*;

import java.util.*;
import java.util.List;


import com.google.gson.Gson;

import com.springapp.mvc.model.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;


@Controller
public class HelloController {
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        //return "redirect:/pages/search_image.html";
        return "redirect:/pages/audio_player.html";

	}

    @Autowired
    ServletContext servletContext;
    private final String  String str_token="6dd67a74b219a92efeb3b03ce65a126c882001f153cd05508a3b5b3942261582a11421ef2802eec972044";




    @RequestMapping(value="/test",params = {"query"},method = RequestMethod.GET)
    @ResponseBody
    public List<GoogleResults.Result> print(@RequestParam(value = "query") String query) throws IOException {

        String address = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";
        String charset = "UTF-8";

        URL url = new URL(address + URLEncoder.encode(query, charset));
        Reader reader = new InputStreamReader(url.openStream(), charset);
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

        int total = results.getResponseData().getResults().size();
        System.out.println("total: "+total);


        for(int i=0; i<=total-1; i++){
            System.out.println("Title: " + results.getResponseData().getResults().get(i).getTitle());
            System.out.println("URL: " + results.getResponseData().getResults().get(i).getUrl() + "\n");

        }

       return  results.getResponseData().getResults();
    }

    /*@RequestMapping(value = "/searchvkmusic",params = {"query"}, method = RequestMethod.GET)
    @ResponseBody
    public List<BackboneModel> search_music(@RequestParam(value = "query") String query) throws Exception {


        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://api.pleer.com/token.php");
        post.addHeader("Authorization", "");
        StringBody comment = new StringBody("client_credentials", ContentType.TEXT_PLAIN);

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("grant_type", comment)
                .build();

         post.setEntity(reqEntity);

        HttpResponse response = client.execute(post);

        String charset = "UTF-8";
        Reader reader = new InputStreamReader(response.getEntity().getContent());
        token token_access =new Gson().fromJson(reader, token.class);
        client = new DefaultHttpClient();
        HttpPost post_plleer = new HttpPost("http://api.pleer.com/index.php");


        post_plleer.setHeader("Content-Type", "application/x-www-form-urlencoded");

        String bodystr="query="+query+"&" +
                "access_token="+token_access.getAccess_token()+"&" +
                "method=tracks_search";
        HttpEntity entity = new ByteArrayEntity(bodystr.getBytes("UTF-8"));


        post_plleer.setEntity(entity);

        HttpResponse response_1 = client.execute(post_plleer);

        reader = new InputStreamReader(response_1.getEntity().getContent());
       // DownloadUrl   list_url =new Gson().fromJson(reader, token.class);




        PleerResults results=new Gson().fromJson(reader, PleerResults.class);


        List<BackboneModel> return_result=download(token_access.getAccess_token(),results.getTracks());
        for (BackboneModel temp:return_result){
            System.out.println(temp.getTrack()+" : "+temp.getArtist()+" : "+temp.getUrl());
        }





        return return_result;




    }      */

    public List<BackboneModel> download(String token, Map<String, PleerResults.track> map_id) throws IOException {
        List<BackboneModel> backboneModels=new ArrayList<BackboneModel>();
        Iterator<Map.Entry<String,PleerResults.track >> iter = map_id.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, PleerResults.track> entry = iter.next();
            HttpClient client = new DefaultHttpClient();
            HttpPost post_plleer = new HttpPost("http://api.pleer.com/index.php");


            post_plleer.setHeader("Content-Type", "application/x-www-form-urlencoded");

            String bodystr="track_id="+entry.getValue().getId()+"&" +
                    "access_token="+token+"&" +
                    "method=tracks_get_download_link&"+
                    "reason=listen";
            HttpEntity entity;
            entity = new ByteArrayEntity(bodystr.getBytes("UTF-8"));

            
           
            post_plleer.setEntity(entity);

            HttpResponse response_1 = client.execute(post_plleer);

            Reader reader = new InputStreamReader(response_1.getEntity().getContent());
            DownloadUrl results=new Gson().fromJson(reader, DownloadUrl.class);
            backboneModels.add(new BackboneModel(results.getUrl(),entry.getValue().getArtist(),
                    entry.getValue().getTrack()));

            System.out.println(entry.getValue().getId());

        }

        return backboneModels;




    }

    @RequestMapping(value = "/mp3/{file_id}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("file_id") String fileName,
            HttpServletResponse response) throws UnsupportedAudioFileException {
        try {
           
            String file_str="https://api.vkontakte.ru/method/audio.getById?" +
                    "audios="+fileName+
                    "&access_token="+str_token;
            URL  file_url=new URL(file_str);
            System.out.println(file_str);



            //InputStreamReader is =new InputStreamReader(music.openStream());
            //IOUtils.copy(is, response.getOutputStream());

            URLConnection conn = file_url.openConnection();
            InputStream is = conn.getInputStream();

            System.out.println("1 connection ");

            UrlResults results=new Gson().fromJson(new InputStreamReader(is), UrlResults.class);
            is.close();

            System.out.println(results.getResponse().get(0).getUrl());

            URLConnection conn_2=new URL(results.getResponse().get(0).getUrl()).openConnection();
            InputStream is_d = conn_2.getInputStream();

            System.out.println("2 connection ");

            OutputStream outstream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int len;
            while ((len = is_d.read(buffer)) > 0) {
                outstream.write(buffer, 0, len);
            }
            outstream.close();
            System.out.println("response fluch");


            response.flushBuffer();
        } catch (IOException ex) {
            //log.info("Error writing file to output stream. Filename was '" + fileName + "'");
            throw new RuntimeException("IOError writing file to output stream");
        }

    }

    @RequestMapping(value = "/searchvkmusic",params = {"query"}, method = RequestMethod.GET)
    @ResponseBody
    public List<VkResults.track> search_music(@RequestParam(value = "query") String query) throws Exception {

        
        HttpClient client = new DefaultHttpClient();
        HttpGet Vk_get_serch_music = new HttpGet("https://api.vkontakte.ru/method/audio.search?q="+URLEncoder.encode(query,"UTF-8")+"&count=10&v=5.21&access_token="+str_token);
        HttpResponse response_1 = client.execute(Vk_get_serch_music);

        Reader reader = new InputStreamReader(response_1.getEntity().getContent());
        VkResults results=new Gson().fromJson(reader, VkResults.class);

        /*BufferedReader asd=new BufferedReader(new InputStreamReader(response_1.getEntity().getContent()));
        String s="";
        while((s=asd.readLine())!=null){
            System.out.println(s);

        } */



         return results.getResponse().getItems();




    }

    /*public class PrettyPrintingMap<K, V> {
        private Map<K, V> map;

        public PrettyPrintingMap(Map<K, V> map) {
            this.map = map;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Iterator<Map.Entry<K, V>> iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<K, V> entry = iter.next();
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
                if (iter.hasNext()) {
                    sb.append(',').append(' ');
                }
            }
            return sb.toString();

        }
    }*/





}