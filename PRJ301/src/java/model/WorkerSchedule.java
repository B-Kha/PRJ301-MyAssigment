/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Laptop Acer
 */
public class WorkerSchedule {
    private int wsid; // Mã định danh cho WorkerSchedule
    private Sdeplant sdeplant; // Mã chiến dịch (Schedule Campaign ID)
    private Employee empolyee;  // Mã nhân viên (Employee ID)
    private int quantity; // Số lượng công việc được giao

    public int getWsid() {
        return wsid;
    }

    public void setWsid(int wsid) {
        this.wsid = wsid;
    }

    public Sdeplant getSdeplant() {
        return sdeplant;
    }

    public void setSdeplant(Sdeplant sdeplant) {
        this.sdeplant = sdeplant;
    }

    public Employee getEmpolyee() {
        return empolyee;
    }

    public void setEmpolyee(Employee empolyee) {
        this.empolyee = empolyee;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}  