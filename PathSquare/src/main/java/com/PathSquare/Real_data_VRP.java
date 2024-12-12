package com.PathSquare;
import java.io.IOException;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Real_data_VRP {

    private static final String API_KEY = "5b3ce3597851110001cf6248f1605f53929b4a02ad86b7b18545e346";
    private static final String API_URL = "https://api.openrouteservice.org/geocode/search";
    private static final String DISTANCE_API_URL = "https://api.openrouteservice.org/v2/matrix/driving-car";

    // This method fetches latitude and longitude from the OpenRouteService API using the address.
    public static Node getCoordinatesFromAddress(String address, int nodeId) {
        String url = API_URL + "?api_key=" + API_KEY + "&text=" + address;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();

                JsonObject jsonObject = JsonParser.parseString(responseData).getAsJsonObject();
                JsonArray features = jsonObject.getAsJsonArray("features");
                if (features.size() > 0) {
                    JsonObject geometry = features.get(0).getAsJsonObject().getAsJsonObject("geometry");
                    double longitude = geometry.getAsJsonArray("coordinates").get(0).getAsDouble();
                    double latitude = geometry.getAsJsonArray("coordinates").get(1).getAsDouble();
                    return new Node(nodeId, address, latitude, longitude);
                } else {
                    System.out.println("Address not found: " + address);
                }
            } else {
                System.out.println("Request failed with code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;  // If we can't retrieve coordinates, return null.
    }

    // This method calculates the distance matrix between all nodes (in kilometers).
    public static double[][] calculateAllDistances(List<Node> nodes) {
        double[][] distanceMatrix = new double[nodes.size()][nodes.size()];

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (i != j) {
                    distanceMatrix[i][j] = getDistance(nodes.get(i), nodes.get(j));
                } else {
                    distanceMatrix[i][j] = 0.0;  // Distance to itself is 0
                }
            }
        }
        return distanceMatrix;
    }

    // This method calculates the driving distance between two nodes using the OpenRouteService API.
    public static double getDistance(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return 0.0;  // If either node is null, return 0 distance.
        }

        String url = DISTANCE_API_URL + "?api_key=" + API_KEY;
        String body = String.format("{\"locations\":[[%f,%f],[%f,%f]]}", node1.getLongitude(), node1.getLatitude(), node2.getLongitude(), node2.getLatitude());

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(body, MediaType.get("application/json"));
        Request request = new Request.Builder().url(url).post(requestBody).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(responseData).getAsJsonObject();
                JsonArray rows = jsonObject.getAsJsonArray("durations").get(0).getAsJsonArray();
                return rows.get(1).getAsDouble() / 60;  // Convert duration from meters to kms
            } else {
                System.out.println("Request failed with code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;  // Default value if distance can't be retrieved.
    }
    
    public static void solveVRP(List<Node> nodes, double[][] distances) {
        boolean[] visited = new boolean[nodes.size()];
        int currentNode = 0; // Always start at node 0 (the first address)
        visited[currentNode] = true;
        double totalDistance = 0.0;
    
        // Store the route to display later
        StringBuilder route = new StringBuilder();
        route.append("Starting at: ").append(nodes.get(currentNode).getAddress());
    
        // Visit the other nodes in the greedy order, but the first node is fixed.
        for (int i = 1; i < nodes.size(); i++) {
            double minDistance = Double.MAX_VALUE;
            int nextNode = -1;
    
            // Find the closest unvisited node
            for (int j = 0; j < nodes.size(); j++) {
                if (!visited[j] && distances[currentNode][j] < minDistance) {
                    minDistance = distances[currentNode][j];
                    nextNode = j;
                }
            }
    
            // Visit the next closest node
            visited[nextNode] = true;
            totalDistance += minDistance;
            currentNode = nextNode;
    
            // Append the address of the next node to the route
            route.append(" -> ").append(nodes.get(currentNode).getAddress());
        }
    
        // Print the total route and total distance
        System.out.println("\nOptimal route:");
        System.out.println(route.toString()); // Display the full route
        System.out.println("Total distance: " + totalDistance + " km");
    }
    
    }