package com.thinvent.zhhd.task;


public abstract class ProcessUnit implements IProcessUnit, Runnable {

    private Thread thread = null;
    private boolean isCanceled = false;

    public ProcessUnit()
    {
        thread = new Thread(this);
    }

    public void start()
    {
        thread.start();
    }

    public void stop()
    {
        thread.interrupt();
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void checkCancel()
    {
        if(thread.isInterrupted()) {
            isCanceled = true;
            try {
                onCancel();
            } catch (Exception e) {
                System.out.println("任务结束时发生错误");
                e.printStackTrace();
            }
        }
    }

    public void onCancel() {

    }

    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            this.stop();
        }
    }
}
