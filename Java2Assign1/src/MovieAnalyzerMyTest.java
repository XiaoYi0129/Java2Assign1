import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MovieAnalyzerMyTest {
    public static void main(String[] args) throws IOException {
        MovieAnalyzer movieAnalyzer=new MovieAnalyzer
                ("/Users/xiaoyi/IdeaProjects/java2/src/Homework1/imdb_top_500.csv");///Users/xiaoyi/IdeaProjects/java2/src/Homework1/
        //System.out.println(movieAnalyzer.MovieStream);

        //problem1
        //Map<Integer,Integer> MovieYear = movieAnalyzer.getMovieCountByYear();
        //System.out.println(MovieYear);

        //problem2
        //Map<String,Integer> MovieGenre = movieAnalyzer.getMovieCountByGenre();
        //System.out.println(MovieGenre);

        //problem3
        //Map<List<String>,Integer> coStar=movieAnalyzer.getCoStarCount();
        //System.out.println(coStar);

        //problem4
        //List<String> getTopMovies = movieAnalyzer.getTopMovies(30,"runtime");
        //List<String> getTopMovies = movieAnalyzer.getTopMovies(30,"overview");
        //System.out.println(getTopMovies);

        //problem5
        //int a=40;
        // int  b=3;
        //float c=a/b;
        // System.out.println(c);
        //List<String> getTopStars = movieAnalyzer.getTopStars(60,"rating");
        //problem： James Caan被提前了rating
        //[Bob Gunton, Tim Robbins, William Sadler, Aaron Eckhart, Heath Ledger, John Fiedler, Martin Balsam, Caroline Goodall, John Travolta, Aldo Giuffrè, Eli Wallach, Elijah Wood, Elliot Page, Gary Sinise, Joseph Gordon-Levitt, Meat Loaf, Sally Field, Sean Bean, Zach Grenier, Diane Keaton, Keanu Reeves, Laurence Fishburne, Lilly Wachowski, Lorraine Bracco, Louise Fletcher, Michael Berryman, Peter Brocco, Ray Liotta, Akira Ishihama, Alexandre Rodrigues, Andrew Kevin Walker, Aparna Balamurali, Bonnie Hunt, Cho Yeo-jeong, Choi Woo-sik, Daveigh Chase, Donna Reed, Edward Burns, Giorgio Cantarini, Giustino Durano, James Caan, Kasi Lemmons, Keiko Tsushima, Kátia Lund, Lawrence A. Bonney, Leandro Firmino, Lee Sun-kyun, Leslie Odom Jr., Lin-Manuel Miranda, Lionel Barrymore, Mackenzie Foy, Matheus Nachtergaele, Michael Clarke Duncan, Nicoletta Braschi, Orlando Bloom, Phillipa Soo, Renée Elise Goldsberry, Roberto Benigni, Rumi Hiiragi, Shima Iwashita]
        //[Bob Gunton, Tim Robbins, William Sadler, Aaron Eckhart, Heath Ledger, John Fiedler, Martin Balsam, Caroline Goodall, John Travolta, Aldo Giuffrè, Eli Wallach, Elijah Wood, Elliot Page, Gary Sinise, Joseph Gordon-Levitt, Meat Loaf, Sally Field, Sean Bean, Zach Grenier, Diane Keaton, Keanu Reeves, Laurence Fishburne, Lilly Wachowski, Lorraine Bracco, Louise Fletcher, Michael Berryman, Peter Brocco, Ray Liotta, Akira Ishihama, Alexandre Rodrigues, Andrew Kevin Walker, Aparna Balamurali, Bonnie Hunt, Cho Yeo-jeong, Choi Woo-sik, Daveigh Chase, Donna Reed, Edward Burns, Giorgio Cantarini, Giustino Durano, James Caan, Kasi Lemmons, Keiko Tsushima, Kátia Lund, Lawrence A. Bonney, Leandro Firmino, Lee Sun-kyun, Leslie Odom Jr., Lin-Manuel Miranda, Lionel Barrymore, Mackenzie Foy, Matheus Nachtergaele, Michael Clarke Duncan, Nicoletta Braschi, Orlando Bloom, Phillipa Soo, Renée Elise Goldsberry, Roberto Benigni, Rumi Hiiragi, Shima Iwashita]
        //List<String> getTopStars = movieAnalyzer.getTopStars(60,"gross");
        //System.out.println(getTopStars);

        //problem6
        //List<String> searchMovie = movieAnalyzer.searchMovies("Drama",1,1000);
        //System.out.println(searchMovie);

        //after test
        //problem 4 runtime
        //find1: 少了一个，Drishyam，重名电影,只保留了时长163的那个,160的无了
        //List<String> getTopMovies = movieAnalyzer.getTopMovies(30,"runtime");
        //System.out.println(getTopMovies);

        //problem 5 rating with
        //find Elijah Wood 这个人多出来了
        // movieList 有他，他有三个电影
        //movieTreeMap里面也有他，和他三个评分：[8.9, 8.8, 8.7]
        //sort里面也有他，还有他的平均数 8.8
        //排好序后他排到第11个
        //精度问题,看文档
        List<String> getTopStars = movieAnalyzer.getTopStars(100,"rating");
        System.out.println(getTopStars);


        //problem 2 genre with
        //排序后出问题
       // Map<String,Integer> MovieGenre = movieAnalyzer.getMovieCountByGenre();
       //System.out.println(MovieGenre);

    }
}
