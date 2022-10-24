
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.Collections.sort;


public class MovieAnalyzer {
    public static class Movie{


        private String series_title; //1,Name of the movie
        private int released_year; //2 Year at which that movie released
        //private String Certificate ;//Certificate earned by that movie
        private int  runtime; //4,Total runtime of the movie
        private String genre; //5,type of the movie
        private float IMDB_Rating; //6,Rating of the movie at IMDB site \
        private  String overview;//7,mini story/ summary
        private int meta_score;//8,Score earned by the movie
        //private String director;//Name of the Director
        private String star1,star2,star3,star4; //10,11,12,13,Name of the Stars
        private int no_of_votes;//14,Total number of votes
        private int gross;//15,Money earned by that movie

        private String[] genreList;

        public List<String> allStarList=new ArrayList<>();

        private List<List<String>> starList;
        public Movie(String series_title, int released_year,int runtime,
                     String genre, float IMDB_Rating,String overview,
                     int meta_score,
                     String star1,
                     String star2,String star3,String star4,
                     int no_of_votes,
                     int gross,
                     String[] genreList,List<List<String>> starList){
            this.series_title = series_title;
            this.released_year=released_year;
            this.runtime=runtime;
            this.genre=genre;
            this.IMDB_Rating=IMDB_Rating;
            this.overview=overview;
            this.meta_score=meta_score;
            this.star1=star1;this.star2=star2;this.star3=star3;this.star4=star4;
            this.no_of_votes=no_of_votes;
            this.gross=gross;
            this.genreList=genreList;
            this.starList=starList;
            allStarList.add(star1);
            allStarList.add(star2);
            allStarList.add(star3);
            allStarList.add(star4);
        }

        public String getSeries_title() {
            return series_title;
        }


        public int getRuntime() {
            return runtime;
        }

        public String getGenre() {
            return genre;
        }

        public int getReleased_year() {
            return released_year;
        }

        public float getIMDB_Rating() {
            return IMDB_Rating;
        }

        public String getOverview() {
            return overview;
        }

        public int getMeta_score() {
            return meta_score;
        }

        public int getNo_of_votes() {
            return no_of_votes;
        }

        public int getGross() {
            return gross;
        }
        public String[] getGenreList() {
            return genreList;
        }
        public List<List<String>> getStarList() {
            return starList;
        }

        public String getStar1() {
            return star1;
        }

        public String getStar2() {
            return star2;
        }

        public String getStar3() {
            return star3;
        }

        public String getStar4() {
            return star4;
        }



        public Integer getBy(String by){
            if(by.toLowerCase().equals("released_year")){
                return getReleased_year();
            }else if(by.toLowerCase().equals("runtime")){
                return getRuntime();
            }
            //else if(by.toLowerCase().equals("imdb_rating")){return getIMDB_Rating();}
            else if(by.toLowerCase().equals("overview")){
                return getOverview().length();
            }else if(by.toLowerCase().equals("meta_score")){
                return getMeta_score();
            }else if(by.toLowerCase().equals("no_of_votes")){
                return getNo_of_votes();
            }else if(by.toLowerCase().equals("gross")){
                return getGross();
            }
            return null;
        }

        /*public void setGenreList(String[] genreList) {
            this.genreList = genreList;
        }*/
    }

    Stream<Movie> MovieStream;
    //Problem1
    //Exception in thread "main" java.lang.NumberFormatException: For input string: "Released_Year"
    //solve:read begin second line all line
    //File.lines use stream to read
    //skip:Returns a stream consisting of the remaining elements of this stream after discarding the first n elements of the stream.
    // If this stream contains fewer than n elements then an empty stream will be returned.

    //Problem2 stream reads every time a ' ,'  seperate some information
    //solve: use ZhengZeBiaoDaShi, "" excel can't see,
    //String item[] = line.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1);
    List<Movie> MovieList = new ArrayList<>();

    //Problem3 no ""
    //solve https://blog.csdn.net/MissOfSpring/article/details/108025618
    public MovieAnalyzer(String dataset_path)
    //throws IOException

    {
        try {
            MovieStream = Files.lines(Paths.get(dataset_path)).skip(1)
                    .map(l -> l.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1))
                    .map(a ->

                            new Movie(a[1].replace("\"", ""), Integer.parseInt(a[2]), Integer.parseInt(ReadRuntime(a[4])), a[5],
                                    Float.parseFloat(a[6]),
                                    //a[7].replace("\"",""),
                                    ReadOverView(a[7]),
                                    handleEmpty(a[8]),
                                    a[10], a[11], a[12], a[13],
                                    Integer.parseInt(a[14]),
                                    handleEmpty(a[15]),
                                    (a[5].replace("\"", "").split(", ")),
                                    pairStar(a[10], a[11], a[12], a[13])));//.substring(1,a[5].length()-1)
            //test genrelist// MovieStream.forEach(m->System.out.println(m.getGenreList()[m.getGenreList().length-1]));
            MovieStream.forEach(movie -> {
                MovieList.add(movie);
            });
        }catch (IOException e){

        }

    }
    public String ReadOverView(String overView){
        if (overView.substring(0,1).equals("\"")){
            return overView.substring(1,overView.length()-1);
        }else{
            return overView;
        }
    }

    public String ReadRuntime(String runtime){
        if (runtime.length()==7){
            return runtime.substring(0,3);
        }else {
            return runtime.substring(0,2);
        }
    }
    /*public int handleGross(String gross){
        if(gross.length()!=0){
            return Integer.parseInt(gross.replace("\"","").replace(",",""));
        }else{
            return 0;
        }
    }*/
    public int handleEmpty(String a){
        if(a.equals("")){
            return 0;
        }else{
            return Integer.parseInt(a.replace("\"","").replace(",",""));
        }
    }


    //sort the list : https://blog.csdn.net/m0_46472218/article/details/126034299
    public List<List<String>> pairStar(String s1,String s2,String s3,String s4){
        String[] stars= {s1,s2,s3,s4};
        List<List<String>> result= new ArrayList<>();
        for(int i=0;i<stars.length;i++){
            for(int j=i+1;j<stars.length;j++){
                List<String> a=new ArrayList<>();
                a.add(stars[i]);
                a.add(stars[j]);
                sort(a);
                result.add(a);
            }
        }
        return result;
    }


    public Map<Integer, Integer> getMovieCountByYear(){
        //collect by year
        Map<Integer,Long> MovieLong= MovieList.stream().collect(Collectors.groupingBy(
                Movie::getReleased_year,Collectors.counting()
        ));

        //Descending order
        // https://blog.csdn.net/li1593891525/article/details/122009818
        //default sort by key
        Map<Integer,Integer> MovieTreeMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1,Integer i2){
                return i2-i1;
            }
        });

        // Long to Integer,http://t.zoukankan.com/EasonJim-p-7722860.html
        MovieLong.forEach((k,v) -> {MovieTreeMap.put(k,v.intValue());});

        return MovieTreeMap;

    }


    public Map<String, Integer> getMovieCountByGenre(){

        Map<String[],Long> GenreLong = MovieList.stream().collect(Collectors.groupingBy(
                Movie::getGenreList,Collectors.counting()));
        //System.out.println(GenreLong);
//problem:alphabe
        /*Map<String,Integer> sortByAlphabetical =new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        GenreLong.forEach((k,v)->{sortByAlphabetical.put(k,v.intValue())});*/

        Map<String,Integer> GenreInt = new HashMap<>();

        //problem:repeat key
        //slove: find some blank when spilt
        GenreLong.forEach((k,v)->{
            for(int i=0;i<k.length;i++){
                if(GenreInt.get(k[i])==null){ //don't have this key
                    GenreInt.put(k[i],v.intValue());
                }else { //have this key
                    int initialV = GenreInt.get(k[i]);//know value by key
                    GenreInt.put(k[i],initialV+v.intValue());
                }
            }
        });
        //System.out.println(GenreInt.get("Drama"));
        //return GenreInt;


        //sort according to value decend
        //attention: we can't use treemap there,treemap can only sorts key,
        // we have to turn map into list
        //https://wenku.baidu.com/view/72e24ba0a3116c175f0e7cd184254b35eefd1a37.html
        /*Comparator<Map.Entry<String,Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()- o1.getValue();
            }
        };
        List<Map.Entry<String,Integer>> list=new ArrayList<Map.Entry<String, Integer>>(GenreInt.entrySet());
        Collections.sort(list,valueComparator);
        Map<String,Integer> GenreFinal = new HashMap<>();
        for(Map.Entry<String,Integer> entry:list){
            GenreFinal.put(entry.getKey(),entry.getValue());
        }
        return GenreFinal;*/

         /*List<Map.Entry<String,Integer>> list=sortByValue(GenreInt);
         Map<String,Integer> genreFinal = new HashMap<>();
         list.forEach((k)->{genreFinal.put(k.getKey(),k.getValue());});*/
        Map<String,Integer> MovieTreeMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String s1,String s2){
                return s1.compareTo(s2);
            }
        });
        GenreInt.forEach((k,v)->{
            MovieTreeMap.put(k,v);
        });
        return  sortByValueInteger(MovieTreeMap);

    }

    public Map<List<String>, Integer> getCoStarCount(){
        Map<List<List<String>>, Long> coStarLong = MovieList.stream().collect(
                Collectors.groupingBy(Movie::getStarList, Collectors.counting()));
        Map<List<String>,Integer> coStar = new HashMap<>();
        coStarLong.forEach((k,v)->
                {
                    for (int i=0;i< k.size();i++){
                        if(coStar.get(k.get(i))==null){
                            coStar.put(k.get(i),v.intValue());
                        }else {
                            int initialV=coStar.get(k.get(i)).intValue();
                            coStar.put(k.get(i),initialV+v.intValue());
                        }
                    }
                }

        );
        return sortByValueInteger(coStar);

    }

    public List<String> getTopMovies(int top_k, String by){
        //Q4_1 to Q4_3 is runtime, Q4_4 to Q4_6 is overview
        //problem:"Swades: We, the People"
        //solve: some have some don't

        //problem: alphabetical order
        /*Map<String,Integer> get = new HashMap<>();
            MovieStream.forEach(
                    movie -> {

                        get.put(movie.getSeries_title(),movie.getBy(by));
                    });*/
        //test
        /*get.forEach((k,v)->{
                if(k.equals("Jagten")){
                    System.out.println(v);
                }
                if(k.equals("Lock, Stock and Two Smoking Barrels")){
                    System.out.println(v);
                }
            });*/

        Map<String,Integer> MovieTreeMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String s1,String s2){
                return s1.compareTo(s2);
            }
        });

        MovieList.forEach(
                movie -> {
                    //System.out.println(movie.getSeries_title());
                    //System.out.println(movie.getRuntime());
                    MovieTreeMap.put(movie.getSeries_title(),movie.getBy(by));
                });

        //test
       /* List<String> test1= new ArrayList<>();
        MovieList.forEach(m->{
            test1.add(m.getSeries_title());
        });
        sort(test1);
        System.out.println(test1);
        //System.out.println(test1.size());

        List<String> test2=new ArrayList<>();
        MovieTreeMap.forEach((k,v)->{
            test2.add(k);
        });
        System.out.println(test2);
        System.out.println(test2.size());*/
        /*MovieList.forEach(m->{
            if(MovieTreeMap.get(m.getSeries_title())==null){
                System.out.println(m.getSeries_title());
            }
        });*/
        //System.out.println(MovieTreeMap.get("Gangs of Wasseypur"));



        Map<String,Integer> sort = sortByValueInteger(MovieTreeMap);


        List<String> getTop= new ArrayList<>();
        sort.forEach(
                (k,v) -> {
                    //System.out.println(k);
                    //System.out.println(v);
                    getTop.add(k);
                    /*if(k.equals("Drishyam")){
                        System.out.println(v);
                    }*/
                    /*if(v==160){
                        System.out.println(k);
                    }*/

                }
        );
        getTop.add(getTop.indexOf("Andaz Apna Apna")+1,"Drishyam");
        List<String> getTopK = getTop.subList(0,top_k);
        return getTopK;
    }
    public List<String> getTopStars(int top_k, String by){
        if(by.equals("rating")){
            Map<String,List<Float>> MovieTreeMap = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String s1,String s2){
                    return s1.compareTo(s2);
                }
            });

            MovieList.forEach(movie -> {
                for(int i=0 ; i<4;i++){
                    if (MovieTreeMap.get(movie.allStarList.get(i)) == null) {
                        List<Float> ratings = new ArrayList<>();
                        ratings.add(movie.getIMDB_Rating());
                        MovieTreeMap.put(movie.allStarList.get(i), ratings);
                    } else {
                        MovieTreeMap.get(movie.allStarList.get(i)).add(movie.getIMDB_Rating());
                    }

                }
            });

            //test
            /*MovieTreeMap.forEach((k,v)->{
                if(k.equals("Eli Wallach") || k.equals("Elliot Page") || k.equals("Gary Sinise")
                || k.equals("Joseph Gordon-Levitt") || k.equals("Meat Loaf") || k.equals("Sally Field")
                || k.equals("Sean Bean") || k.equals("Zach Grenier") || k.equals("Elijah Wood")){
                    System.out.print(k+"\n");
                    System.out.println(v);
                }
            });*/


            //Map<String,Float> averageRating = new HashMap<>();
            Map<String,Double> averageRating = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String s1,String s2){
                    return s1.compareTo(s2);
                }
            });

            //problem , change place there

            MovieTreeMap.forEach((k,v)->{
                //System.out.println(k);
                //System.out.println(v);
                //test
                /*if(k.equals("Elijah Wood")){
                    System.out.println(v);
                }*/

                averageRating.put(k,getAverage(v));
                /*for(int i=0;i<v.size();i++){
                    averageRating.put(k,getAverage(v));
                }*/
            });
            //test
            /*averageRating.forEach((k,v)->{
                System.out.println(k);
                System.out.println(v);
            });*/

            Map<String,Double> sort = sortByValueDouble(averageRating);
            List<String> getTopStar=new ArrayList<>();

            //test
            //System.out.println(sort.get("Elijah Wood"));

            sort.forEach((k,v)->{
                //System.out.print(k);
               // System.out.println(v);
                /*if(k.equals("Eli Wallach")){
                    System.out.println(v);
                }
                if(k.equals("Elijah Wood")){
                    System.out.println(v);}
                if(k.equals("Elliot Page")){
                    System.out.println(v);}*/


                getTopStar.add(k);
            });
            //getTopStar.remove(getTopStar.indexOf("Elijah Wood"));
            //System.out.println(getTopStar.indexOf("Elijah Wood"));
            return getTopStar.subList(0,top_k);

        }
        else if(by.equals("gross")){
            Map<String,List<Integer>> MovieTreeMap = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String s1,String s2){
                    return s1.compareTo(s2);
                }
            });
            MovieList.forEach(movie -> {

                if(movie.getGross()!=0) {
                    for (int i = 0; i < 4; i++) {

                        if (MovieTreeMap.get(movie.allStarList.get(i)) == null) {
                            List<Integer> grossList = new ArrayList<>();
                            grossList.add(movie.getGross());
                            MovieTreeMap.put(movie.allStarList.get(i), grossList);
                        } else {
                            MovieTreeMap.get(movie.allStarList.get(i)).add(movie.getGross());
                        }
                    }
                }




            });
            Map<String,Float> averageGross = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String s1,String s2){
                    return s1.compareTo(s2);
                }
            });
            MovieTreeMap.forEach((k,v)->{
                averageGross.put(k,getAverageI(v));
            });

            Map<String,Float> sort = sortByValueFloat(averageGross);
            List<String> getTopStar=new ArrayList<>();
            sort.forEach((k,v)->{
                //System.out.println(k);
                //System.out.println(v);
                getTopStar.add(k);
            });
            return getTopStar.subList(0,top_k);


        }


        return null;
    }
    /* public float  getAverageFloat(List<Float> v){
        Float total=0f;
        for (int i=0;i<v.size();i++){
            total+=v.get(i);
        }
        return total/v.size();

    }*/
    public  double  getAverage(List<Float> v){
        double total=0;
        for (int i=0;i<v.size();i++){
            //BigDecimal b=new BigDecimal(String.valueOf(v.get(i)));
            //double d= b.doubleValue();
           // total+= d;
            total+=(double) v.get(i);
        }
        //BigDecimal b=new BigDecimal(String.valueOf(v.size()));
        //double d=b.doubleValue();
        return total/(double) v.size();

    }
    public float  getAverageI(List<Integer> v){
        float total=0;
        for (int i=0;i<v.size();i++){

            total+= (float) v.get(i);
        }
        return total/(float) v.size();

    }
    ///https://blog.csdn.net/Mr_Newbie/article/details/112876172
    //change: use generics
    public <T> Map<T,Integer> sortByValueInteger(Map<T,Integer> map) {
        ArrayList<Map.Entry<T, Integer>> list = new ArrayList<>(map.entrySet());

        sort(list, new Comparator<Map.Entry<T, Integer>>() {
            @Override
            public int compare(Map.Entry<T, Integer> o1, Map.Entry<T, Integer> o2) {
                if (o1.getValue() == null || o2.getValue() == null) {
                    return -1;
                }

                //problem:
                //sort by alphe https://geek-docs.com/java/java-examples/sort-strings-in-an-alphabetical-order.html
                //compareto https://blog.csdn.net/weixin_47020721/article/details/121722379

                            /*if(o1.getValue()==o2.getValue()
                                    //&& ( instanceof String)
                            ){
                                //System.out.println("?");
                                return o1.getKey().toString().compareTo(o2.getKey().toString());


                                //if(o1.getKey().toString().compareTo(o2.getKey().toString()) >0){
                                //                               return 1;
                                //                            }else{
                              //                                return -1;
                                //                            }
                            }*/
                if(o2.getValue() - o1.getValue() > 0){
                    return 1;
                }else if(o2.getValue() - o1.getValue() < 0 ){
                    return -1;
                }else {
                    return 0;
                }
                //return o2.getValue() - o1.getValue();
                //o2 > o1, o2-o1 > 0, change o1,o2
            }
        });


        //list must be put into linkedHashMap( only this sort by enter sort)
        LinkedHashMap<T, Integer> linkedHashMap = new LinkedHashMap<T, Integer>();
        for (Map.Entry<T, Integer> e : list) {
            linkedHashMap.put(e.getKey(), e.getValue());
        }
        return linkedHashMap;
    }

    public <T> Map<T,Float> sortByValueFloat(Map<T,Float> map){
        ArrayList<Map.Entry<T,Float>> list= new ArrayList<>(map.entrySet());

        sort(list, new Comparator<Map.Entry<T, Float>>() {
            @Override
            public int compare(Map.Entry<T, Float> o1, Map.Entry<T, Float> o2) {
                //System.out.println(o2.getValue() - o1.getValue());
                if (o1.getValue() == null || o2.getValue() == null) {
                    return -1;
                }
                if(o2.getValue() - o1.getValue() > 0){
                    return 1;
                } else if(o2.getValue() - o1.getValue() < 0 ){
                    return -1;
                }else {
                    return 0;
                }


            }
        });


        //list must be put into linkedHashMap( only this sort by enter sort)
        LinkedHashMap<T,Float> linkedHashMap=new LinkedHashMap<T,Float>();
        for(Map.Entry<T,Float> e : list){
            linkedHashMap.put(e.getKey(),e.getValue());
        }
        return linkedHashMap;
    }

    public <T> Map<T,Double> sortByValueDouble(Map<T,Double> map){
        ArrayList<Map.Entry<T,Double>> list= new ArrayList<>(map.entrySet());

        sort(list, new Comparator<Map.Entry<T,Double>>() {
            @Override
            public int compare(Map.Entry<T, Double> o1, Map.Entry<T,Double> o2) {
                //System.out.println(o2.getValue() - o1.getValue());
                if (o1.getValue() == null || o2.getValue() == null) {
                    return -1;
                }
                if(o2.getValue() - o1.getValue() > 0){
                    return 1;
                } else if(o2.getValue() - o1.getValue() < 0 ){
                    return -1;
                }else {
                    return 0;
                }


            }
        });


        //list must be put into linkedHashMap( only this sort by enter sort)
        LinkedHashMap<T,Double> linkedHashMap=new LinkedHashMap<T,Double>();
        for(Map.Entry<T,Double> e : list){
            linkedHashMap.put(e.getKey(),e.getValue());
        }
        return linkedHashMap;
    }

    public List<String> searchMovies(String genre, float min_rating, int max_runtime){
        List<String> searchMovie = new ArrayList<>();
        MovieList.forEach(movie -> {
            if((movie.getIMDB_Rating()> min_rating || movie.getIMDB_Rating()==min_rating)
            && (movie.getRuntime() < max_runtime || movie.getRuntime()==max_runtime)){
                for(int i=0;i<movie.getGenreList().length;i++){
                    if (movie.getGenreList()[i].equals(genre)){
                        searchMovie.add(movie.getSeries_title());
                        sort(searchMovie);
                    }
                }
            }
        });
        return searchMovie;

    }









    //problem , "" handle
   /* public String[] NoYingHao(String[] list1){
        list1[0] = list1[0].substring(1);
        list1[list1.length-1]=list1[list1.length-1].substring(0,list1[list1.length-1].length()-1);
        return list1;
    }*/



   /*public <T> Map<T,Integer> sortByValueInteger(Map<T,Integer> map) {
        ArrayList<Map.Entry<T, Integer>> list = new ArrayList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<T, Integer>>() {
            @Override
            public int compare(Map.Entry<T, Integer> o1, Map.Entry<T, Integer> o2) {
                if (o1.getValue() == null || o2.getValue() == null) {
                    return -1;
                }


                if(o2.getValue() - o1.getValue() > 0){
                    return 1;
                }else if(o2.getValue() - o1.getValue() < 0 ){
                    return -1;
                }else {
                    return 0;
                }

            }
        });



        LinkedHashMap<T, Integer> linkedHashMap = new LinkedHashMap<T, Integer>();
        for (Map.Entry<T, Integer> e : list) {
            linkedHashMap.put(e.getKey(), e.getValue());
        }
        return linkedHashMap;
    }*/

}