interface Logger {
    default void log() {
        System.out.println("Default logging");
    }
}

class FileLogger implements Logger {
    @Override
    public void log() {
        Logger.super.log(); 
        System.out.println("FileLogger specific logging");
    }
}
