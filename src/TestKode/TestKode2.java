/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestKode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author lenovo
 */
public class TestKode2 {
    public static void main(String[] args) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        String id = currentTime.format(formatter);
        String idWithLeadingZeros = String.format("%012d", Long.parseLong(id));

        System.out.println("ID: " + id);
        System.out.println("ID with leading zeros: " + idWithLeadingZeros);
    }
}
