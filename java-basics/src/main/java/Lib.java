public interface Lib {
    static String getGreeting(){
        int hour=java.time.LocalTime.now().getHour();
        if(hour < 12){
            return "Good Morning";
        }else if(hour < 18){
            return "Good Afternoon";
        }else{
            return "Good Evening";
        }
    }
}
