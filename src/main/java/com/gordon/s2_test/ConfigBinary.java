package com.gordon.s2_test;

/**
 * Created with IntelliJ IDEA.
 * User: gaopeng
 * Date: 13-9-30
 * Time: 下午1:02
 * To change this template use File | Settings | File Templates.
 */
public class ConfigBinary {
    /**
     *
     * @param browser -only is "Firefox","IE","Chrome"
     * @param binaryPath
     */
    public ConfigBinary(final String browser,final String binaryPath){
        if (browser.equals("Firefox")) {
            setFireFoxBinay(binaryPath);
        }else if (browser.equals("Chrome")){
            setChromeBinay(binaryPath);
        }else if (browser.equals("IE")){
            setIEBinay(binaryPath);
        }else {
            System.err.print("错误的浏览器指定");
        }

    }
    public ConfigBinary(){}

    public void setFireFoxBinay(String fireFoxBinay){
        ConfigProfile.firefoxBinary = fireFoxBinay;
    }
    public void setChromeBinay(String chromeBinay){
        ConfigProfile.chromeBinary = chromeBinay;
    }
    public void setIEBinay(String IEBinary){
        ConfigProfile.IEBinary = IEBinary;
    }
}
