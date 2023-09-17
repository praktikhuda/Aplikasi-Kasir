/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestKode;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author lenovo
 */
public class Test {
//private static Set<Integer> angkaDipakai = new HashSet<>();
//
//    public static void main(String[] args) {
//        int angka1 = 5;
//        int angka2 = 10;
//        int angka3 = 5;
//
//        // Mengujikan angka1
//        if (isAngkaDipakai(angka1)) {
//            System.out.println("Angka " + angka1 + " sudah dipakai");
//        } else {
//            System.out.println("Angka " + angka1 + " belum dipakai");
//            angkaDipakai.add(angka1); // Tandai angka1 sebagai sudah dipakai
//        }
//
//        // Mengujikan angka2
//        if (isAngkaDipakai(angka2)) {
//            System.out.println("Angka " + angka2 + " sudah dipakai");
//        } else {
//            System.out.println("Angka " + angka2 + " belum dipakai");
//            angkaDipakai.add(angka2); // Tandai angka2 sebagai sudah dipakai
//        }
//
//        // Mengujikan angka3
//        if (isAngkaDipakai(angka3)) {
//            System.out.println("Angka " + angka3 + " sudah dipakai");
//        } else {
//            System.out.println("Angka " + angka3 + " belum dipakai");
//            angkaDipakai.add(angka3); // Tandai angka3 sebagai sudah dipakai
//        }
//    }
//
//    private static boolean isAngkaDipakai(int angka) {
//        return angkaDipakai.contains(angka);
//    }
    private static int nomorUrut = 8; // Nomor urut awal

    public static void main(String[] args) {
        String tanggal = LocalDate.now().toString();
        String id = tanggal + "-" + getFormattedNomorUrut();

        System.out.println("ID: " + id);
    }

    private static String getFormattedNomorUrut() {
        String formattedNomorUrut = String.format("%03d", nomorUrut);
        nomorUrut++; // Tambahkan nomor urut setiap kali digunakan

        return formattedNomorUrut;
    }
}
