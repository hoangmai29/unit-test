# Task 3: Kiểm thử hiệu năng với JMeter

## Mục tiêu

- Hiểu cách sử dụng JMeter để thực hiện kiểm thử hiệu năng
- Thiết kế kịch bản kiểm thử với các tham số khác nhau
- Phân tích kết quả kiểm thử và viết báo cáo

## Trang web kiểm thử

**https://www.example.com** - Trang web tĩnh đơn giản, phù hợp để kiểm thử hiệu năng mà không vi phạm chính sách sử dụng.

## Cấu trúc thư mục

```
Task_3/
└── jmeter/
    ├── test-plan.jmx     # JMeter test plan
    └── README.md          # Báo cáo kết quả
```

## Kịch bản kiểm thử

### Thread Group 1: Kịch bản cơ bản

| Tham số | Giá trị |
|---------|---------|
| Số lượng người dùng (Threads) | 10 |
| Thời gian chạy (Loop Count) | 5 lần lặp |
| Hành vi | Gửi HTTP GET đến trang chủ (`/`) |
| Assertion | Response code = 200 |

**Mục đích**: Kiểm tra website hoạt động bình thường với tải nhẹ.

### Thread Group 2: Kịch bản tải nặng

| Tham số | Giá trị |
|---------|---------|
| Số lượng người dùng (Threads) | 50 |
| Ramp-up Period | 30 giây |
| Hành vi | GET trang chủ (`/`) + trang con (`/about`) |

**Mục đích**: Mô phỏng 50 người dùng truy cập đồng thời, tăng dần trong 30 giây.

### Thread Group 3: Kịch bản tùy chỉnh

| Tham số | Giá trị |
|---------|---------|
| Số lượng người dùng (Threads) | 20 |
| Thời gian chạy | 60 giây |
| Ramp-up Period | 10 giây |
| Hành vi | GET 2 trang con (`/about`, `/contact`) |

**Mục đích**: Kiểm thử tải liên tục trong 60 giây với nhiều endpoint.

## Listeners (Thu thập kết quả)

- **View Results Tree**: Xem chi tiết từng request/response
- **Summary Report**: Tổng hợp thống kê (Avg, Min, Max, Throughput, Error %)
- **Aggregate Report**: Phân tích chi tiết theo percentile (90%, 95%, 99%)

## Các chỉ số phân tích

| Chỉ số | Mô tả |
|--------|-------|
| **Response Time** | Thời gian phản hồi trung bình (ms) |
| **Throughput** | Số request/giây mà server xử lý được |
| **Error Rate** | Tỷ lệ phần trăm request bị lỗi |
| **Latency** | Thời gian chờ đợi trước khi nhận phản hồi |
| **Percentile (90/95/99)** | Phân bố thời gian phản hồi |

## Kết quả mong đợi

### Thread Group 1 (10 users, 5 loops)
- Response Time: < 500ms
- Error Rate: 0%
- Throughput: Ổn định

### Thread Group 2 (50 users, ramp-up 30s)
- Response Time: Có thể tăng nhẹ do tải
- Error Rate: < 1%
- Throughput: Cao hơn Thread Group 1

### Thread Group 3 (20 users, 60s)
- Response Time: Ổn định trong suốt 60s
- Error Rate: < 1%
- Throughput: Đều đặn

## Cách chạy

### Yêu cầu
- Java JDK 8 trở lên
- Apache JMeter 5.x ([Download](https://jmeter.apache.org/download_jmeter.cgi))

### Chạy với GUI
```bash
# Mở JMeter và load test plan
jmeter -t Task_3/jmeter/test-plan.jmx
```

### Chạy headless (CLI)
```bash
jmeter -n -t Task_3/jmeter/test-plan.jmx -l Task_3/jmeter/results.csv -e -o Task_3/jmeter/report/
```

## Lưu ý

> ⚠️ **KHÔNG** gửi quá nhiều yêu cầu đến website để tránh vi phạm chính sách sử dụng (rate limiting).

## Tài liệu tham khảo
- [Apache JMeter](https://jmeter.apache.org/)
- [JMeter User Manual](https://jmeter.apache.org/usermanual/)
