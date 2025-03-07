package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {
    private PaymentService paymentService;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = Mockito.mock(PaymentRepository.class);
        paymentService = new PaymentServiceImpl(paymentRepository);
    }

    @Test
    void testAddPaymentSuccessVoucher() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Order order = Mockito.mock(Order.class);
        when(order.getId()).thenReturn("1");
        when(order.getOrderTime()).thenReturn(1708560000L);
        when(order.getAuthor()).thenReturn("Safira");

        Payment payment = new Payment(UUID.randomUUID().toString(), PaymentMethod.VOUCHER.getValue(), paymentData);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.addPayment(order, PaymentMethod.VOUCHER.getValue(), paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentRejectedVoucher() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALID1234XYZ");

        Order order = Mockito.mock(Order.class);
        when(order.getId()).thenReturn("2");
        when(order.getOrderTime()).thenReturn(1708560000L);
        when(order.getAuthor()).thenReturn("Bambang");

        Payment payment = new Payment(UUID.randomUUID().toString(), PaymentMethod.VOUCHER.getValue(), paymentData);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.addPayment(order, PaymentMethod.VOUCHER.getValue(), paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentSuccessBankTransfer() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF123456");

        Order order = Mockito.mock(Order.class);
        when(order.getId()).thenReturn("3");
        when(order.getOrderTime()).thenReturn(1708560000L);
        when(order.getAuthor()).thenReturn("Safira");

        Payment payment = new Payment(UUID.randomUUID().toString(), PaymentMethod.BANK_TRANSFER.getValue(), paymentData);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.addPayment(order, PaymentMethod.BANK_TRANSFER.getValue(), paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentRejectedBankTransfer() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "");

        Order order = Mockito.mock(Order.class);
        when(order.getId()).thenReturn("4");
        when(order.getOrderTime()).thenReturn(1708560000L);
        when(order.getAuthor()).thenReturn("Bambang");

        Payment payment = new Payment(UUID.randomUUID().toString(), PaymentMethod.BANK_TRANSFER.getValue(), paymentData);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.addPayment(order, PaymentMethod.BANK_TRANSFER.getValue(), paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetPaymentStatusToSuccess() {
        Payment payment = new Payment("5", PaymentMethod.VOUCHER.getValue(), new HashMap<>());

        when(paymentRepository.findById("5")).thenReturn(payment);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetPaymentStatusToRejected() {
        Payment payment = new Payment("6", PaymentMethod.BANK_TRANSFER.getValue(), new HashMap<>());

        when(paymentRepository.findById("6")).thenReturn(payment);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testGetPaymentById() {
        Payment payment = new Payment("7", PaymentMethod.VOUCHER.getValue(), new HashMap<>());

        when(paymentRepository.findById("7")).thenReturn(payment);

        Payment result = paymentService.getPayment("7");
        assertEquals(payment, result);
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = Arrays.asList(
                new Payment("8", PaymentMethod.VOUCHER.getValue(), new HashMap<>()),
                new Payment("9", PaymentMethod.BANK_TRANSFER.getValue(), new HashMap<>())
        );

        when(paymentRepository.getAllPayments()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();
        assertEquals(2, result.size());
        assertEquals(payments, result);
    }
}
