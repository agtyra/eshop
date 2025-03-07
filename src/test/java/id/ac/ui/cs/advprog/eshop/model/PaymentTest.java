package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> validVoucherData;
    private Map<String, String> invalidVoucherData;
    private Map<String, String> validBankTransferData;
    private Map<String, String> invalidBankTransferData;

    @BeforeEach
    void setup() {
        // Valid Voucher Code
        validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP1234ABC5678");

        // Invalid Voucher Code
        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "INVALID1234XYZ");

        // Valid Bank Transfer
        validBankTransferData = new HashMap<>();
        validBankTransferData.put("bankName", "BCA");
        validBankTransferData.put("referenceCode", "REF123456");

        // Invalid Bank Transfer (empty referenceCode)
        invalidBankTransferData = new HashMap<>();
        invalidBankTransferData.put("bankName", "BCA");
        invalidBankTransferData.put("referenceCode", "");
    }

    @Test
    void testCreatePaymentWithValidVoucher() {
        Payment payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), validVoucherData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidVoucher() {
        Payment payment = new Payment("2", PaymentMethod.VOUCHER.getValue(), invalidVoucherData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentWithValidBankTransfer() {
        Payment payment = new Payment("3", PaymentMethod.BANK_TRANSFER.getValue(), validBankTransferData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidBankTransfer() {
        Payment payment = new Payment("4", PaymentMethod.BANK_TRANSFER.getValue(), invalidBankTransferData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetValidPaymentStatus() {
        Payment payment = new Payment("5", PaymentMethod.VOUCHER.getValue(), validVoucherData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetInvalidPaymentStatus() {
        Payment payment = new Payment("6", PaymentMethod.VOUCHER.getValue(), validVoucherData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("INVALID_STATUS"));
    }
}
