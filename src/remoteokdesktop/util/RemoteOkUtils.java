package remoteokdesktop.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import remoteokdesktop.model.RemoteOkJob;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class RemoteOkUtils {

    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static final String FAVORITES_PATH = "favorites.txt";

    public static List<RemoteOkJob> getAll() {
        HttpResponse<JsonNode> remoteOkResponse = null;
        List<RemoteOkJob> jobs = null;
        try {
            remoteOkResponse = Unirest.get("https://remoteok.io/api").asJson();
            JSONArray arr = remoteOkResponse.getBody().getArray();
            arr.remove(0);
            ObjectMapper mapper = new ObjectMapper();
            jobs = jsonArrayToJobs(arr.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return jobs;
    }

    public static List<RemoteOkJob> getAll(String filter) {
        List<RemoteOkJob> jobs = getAll();

        jobs = filter(filter, jobs);

        return jobs;
    }

    public static void likeJob(RemoteOkJob job) {
        try {

            List<RemoteOkJob> likedJobs = getLiked();
            FileWriter fileWriter = new FileWriter(FAVORITES_PATH);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            likedJobs.add(job);
            printWriter.println(jobArrayToJson(likedJobs));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dislikeJob(RemoteOkJob job) {
        try {
            List<RemoteOkJob> likedJobs = getLiked();
            FileWriter fileWriter = new FileWriter(FAVORITES_PATH);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            likedJobs.removeAll(likedJobs.stream().filter(j -> j.getId().equals(job.getId())).collect(Collectors.toList()));
            printWriter.println(jobArrayToJson(likedJobs));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<RemoteOkJob> getLiked() {
        String result = null;
        try {
            result = Files.readAllLines(Paths.get(FAVORITES_PATH)).stream().collect(Collectors.joining("\n"));
        } catch (NoSuchFileException ex) {
            try {
                Files.createFile(Paths.get(FAVORITES_PATH));
                result = Files.readAllLines(Paths.get(FAVORITES_PATH)).stream().collect(Collectors.joining("\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonArrayToJobs(result);
    }

    public static List<RemoteOkJob> getLiked(String filter) {
        List<RemoteOkJob> jobs = getLiked();

        jobs = filter(filter, jobs);

        return jobs;
    }

    private static List<RemoteOkJob> filter(String filter, List<RemoteOkJob> jobs) {
        if(Objects.nonNull(filter) && !filter.isEmpty()) {
            return jobs.stream()
                    .filter(job -> job.getCompany().contains(filter) || job.getSlug().contains(filter))
                    .collect(Collectors.toList());
        }

        return jobs;
    }

    private static List<RemoteOkJob> jsonArrayToJobs(String stringArr) {
        if(stringArr.isEmpty() || isNull(stringArr)) {
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(stringArr, mapper.getTypeFactory().constructCollectionType(List.class, RemoteOkJob.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static String jobArrayToJson(List<RemoteOkJob> jobs) {
        try {
            return mapper.writeValueAsString(jobs);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Boolean isLiked(List<RemoteOkJob> likedJobs, RemoteOkJob job) {
        return likedJobs.stream().anyMatch((j) -> j.getId().equals(job.getId()));
    }
}
