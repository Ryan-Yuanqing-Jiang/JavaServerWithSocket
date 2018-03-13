package Worker;

import Factory.RequestFactory;
import Model.HTTPRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Not sure if this is the best way to utilize generics.
public class HTTPRequestParser implements RequestParser {
    /**
     * Convert the input stream into a valid HTTPRequest object.
     * @param input The input stream from client socket
     * @return The constructed HTTPRequest
     */
    @Override
    public synchronized HTTPRequest parseRequest(InputStream input) throws InvalidRequestException {

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
        System.out.println("Request Parser " + Thread.currentThread().toString());

        // Read the header first.
        List<String> requestHeader = readRequestHeader(inputReader);
        Map<String, String> headerMap = parseRequestHeader(requestHeader);

        // Parse request line.
        String[] requestLine = parseRequestLine(requestHeader.get(0));

        // Read the body. Has to pass the reference of the same buff reader, so the empty line
        // that separate header and body can be emitted.
        String requestBody = readRequestBody(inputReader);
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RequestFactory.getInstance().generateRequest(requestLine, headerMap, requestBody);
    }

    private String[] parseRequestLine(String line) throws InvalidRequestException {
        String[] requestLine = line.split(" ");
        // System.out.println(line);
        if (requestLine.length == 3) {
            return requestLine;
        } else {
            throw new InvalidRequestException("Invalid request line");
        }
    }

    private Map<String, String> parseRequestHeader(List<String> requestHeader) {
        Map<String, String> headerMap = new HashMap<>();
        for (int i = 1; i < requestHeader.size()-1; i++) {
            String[] headerTuple = requestHeader.get(i).split(": ");
            if (headerTuple.length == 2) {
                headerMap.put(headerTuple[0], headerTuple[1]);
            }
        }
        return headerMap;
    }

    private synchronized List<String> readRequestHeader(BufferedReader inputReader) throws InvalidRequestException {
        List<String> requestHeader = new ArrayList<>();
        String line;
        try {
            line = inputReader.readLine();
            while (!line.isEmpty()) {
                System.out.println(line);
                requestHeader.add(line);
                line = inputReader.readLine();
            }
        } catch (IOException e) {
            throw new InvalidRequestException("Invalid request header");
        } catch (NullPointerException e) {

        }
        return requestHeader;
    }

    private synchronized String readRequestBody(BufferedReader inputReader) throws InvalidRequestException {

        String line;
        StringBuilder requestBody= new StringBuilder();
        try {
            line = inputReader.readLine();
            // System.out.println(line);
            while (line != null) {
                System.out.println(line);
                requestBody.append(line);
                line = inputReader.readLine();
                // System.out.println(line);
            }
        } catch (IOException e) {
            throw new InvalidRequestException("Invalid request body");
        } catch (NullPointerException e) {

        }
        return requestBody.toString();
    }
}
