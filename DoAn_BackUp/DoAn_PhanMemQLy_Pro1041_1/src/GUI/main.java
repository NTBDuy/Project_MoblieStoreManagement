/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Duy
 */
public class main {
    public static void main(String[] args) {
        new SplashScreen().showSplash();
        BasicConfigurator.configure();
    }
}