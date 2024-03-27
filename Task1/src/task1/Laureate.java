package task1;

public class Laureate {

    public int year;
    public String name;
    public String birth_death;
    public String nations;
    public String languages;
    public String genres;
    public String citation;

    public  Laureate(int year,String name, String dob, String nation, String language, String genre, String citation){
        this.year = year;
        this.name = name;
        this.birth_death = dob;
        this.nations = nation;
        this.languages = language;
        this.genres = genre;
        this.citation = citation;
    }

    public int getYear(){
        return this.year;
    }
    public void setYear(int year){
        this.year = year;
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getNations() {
        return nations;
    }

    public void setNations(String nations) {

        this.nations = nations;
    }

    public String getGenre() {

        return genres;
    }

    public void setGenre(String genres) {

        this.genres = genres;
    }

    public String getBirth_death() {

        return birth_death;
    }

    public void setBirth_death(String birth_death) {
        this.birth_death = birth_death;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Year:                    " + year + "\n");
        sb.append("Name:                    " + name + "\n");
        sb.append("Birth/Death:             " + birth_death + "\n");
        sb.append("Nations:                 " + nations + "\n");
        sb.append("Languages:               " + languages + "\n");
        sb.append("Genres:                  " + genres + "\n");
        sb.append("Citations:               " + citation + "\n");

        return sb.toString();
    }
}
