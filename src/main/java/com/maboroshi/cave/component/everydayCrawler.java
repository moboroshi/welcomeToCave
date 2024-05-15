package com.maboroshi.cave.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class everydayCrawler {

    @Scheduled(fixedRate = 24*3600*1000)
    public void executePythonScript() {
        try {
            String pythonScriptPath = "./";

            ProcessBuilder pb = new ProcessBuilder("python", pythonScriptPath);

            Process process = pb.start();

            int exitCode = process.waitFor();

            // 打印 Python 脚本执行结果
            System.out.println("Python 脚本执行完毕，退出码：" + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

