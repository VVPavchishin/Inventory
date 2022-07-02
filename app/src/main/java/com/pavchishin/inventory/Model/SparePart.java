package com.pavchishin.inventory.Model;

import java.util.Objects;

public class SparePart {

    private String partBarcode;
    private String partNumber;
    private String partLocation;
    private String partName;
    private double partQuantity;

    public SparePart() {
    }

    public SparePart(String partNumber, String partLocation, String partName, double partQuantity) {
        this.partNumber = partNumber;
        this.partLocation = partLocation;
        this.partName = partName;
        this.partQuantity = partQuantity;
    }

    public SparePart(String partBarcode, String partNumber, String partLocation, String partName, double partQuantity) {
        this.partBarcode = partBarcode;
        this.partNumber = partNumber;
        this.partLocation = partLocation;
        this.partName = partName;
        this.partQuantity = partQuantity;
    }

    public String getPartBarcode() {
        return partBarcode;
    }

    public void setPartBarcode(String partBarcode) {
        this.partBarcode = partBarcode;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartLocation() {
        return partLocation;
    }

    public void setPartLocation(String partLocation) {
        this.partLocation = partLocation;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPartQuantity() {
        return partQuantity;
    }

    public void setPartQuantity(double partQuantity) {
        this.partQuantity = partQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SparePart)) return false;
        SparePart sparePart = (SparePart) o;
        return Double.compare(sparePart.partQuantity, partQuantity) == 0 && partNumber.equals(sparePart.partNumber) && partLocation.equals(sparePart.partLocation) && partName.equals(sparePart.partName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partNumber, partLocation, partName, partQuantity);
    }
}
