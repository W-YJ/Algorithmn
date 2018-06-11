package Part2.week1.directedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*Bare-bones web crawler*/
public class WebCrawler {
    public static void main(String[] args) {
        //queue of websites to crawl set of marked websites
        Queue<String> queue = new Queue<String>();
        // set of marked websites
        SET<String> marked = new SET<String>();

        // start crawling from root website
        String root = "http://www.princeton.edu";
        queue.enqueue(root);
        marked.add(root);

        while (!queue.isEmpty()) {
            // read in raw html from next website in queue
            String v = queue.dequeue();
            StdOut.print(root);
            In in = new In(v);
            String input = in.readAll();

            // use regular expression to find all URLs in website of form http://xxx.yyy.zzz[crude pattern misses relative URLs]
            String regexp = "http://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                // if unmarked, mark it and put on the queue
                String w = matcher.group();
                if (!marked.contains(w)) {
                    marked.add(w);
                    queue.enqueue(w);
                }
            }
        }
    }
}
