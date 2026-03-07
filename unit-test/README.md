# Task 1: Kiểm thử đơn vị với JUnit 5

## Chủ đề: Phân tích dữ liệu điểm số học sinh

## Mô tả bài toán

Viết một chương trình Java có lớp `StudentAnalyzer` chứa 2 phương thức:

### 1. `countExcellentStudents(List<Double> scores)`
- **Mục đích**: Phân tích điểm số và trả về số lượng học sinh đạt loại Giỏi (>= 8.0)
- **Quy tắc**:
  - Bỏ qua điểm âm hoặc lớn hơn 10 (coi là dữ liệu sai)
  - Nếu danh sách rỗng hoặc null, trả về 0

### 2. `calculateValidAverage(List<Double> scores)`
- **Mục đích**: Tính điểm trung bình của các điểm hợp lệ (từ 0 đến 10)
- **Quy tắc**:
  - Bỏ qua điểm nhỏ hơn 0 hoặc lớn hơn 10
  - Nếu danh sách rỗng hoặc không có điểm hợp lệ, trả về 0

## Cấu trúc thư mục

```
Task_1/
└── unit-test/
    ├── pom.xml                                          # Maven config
    ├── src/
    │   └── main/java/com/ducviet/
    │       └── StudentAnalyzer.java                     # Mã nguồn chính
    └── src/
        └── test/java/com/ducviet/
            └── StudentAnalyzerTest.java                 # Test cases
```

## Các trường hợp kiểm thử

### countExcellentStudents()

| Loại | Test Case | Mô tả |
|------|-----------|-------|
| Bình thường | Điểm hợp lệ + không hợp lệ | `[9.0, 8.5, 7.0, 11.0, -1.0]` → 2 |
| Bình thường | Toàn bộ hợp lệ | `[9.0, 5.5, 8.0, 6.0, 10.0]` → 3 |
| Bình thường | Toàn bộ giỏi | `[8.0, 8.5, 9.0, 9.5, 10.0]` → 5 |
| Bình thường | Không có giỏi | `[5.0, 6.0, 7.0, 7.9]` → 0 |
| Biên | Danh sách trống | `[]` → 0 |
| Biên | Chỉ chứa 0 | `[0.0, 0.0]` → 0 |
| Biên | Chỉ chứa 10 | `[10.0, 10.0]` → 2 (vì 10 >= 8) |
| Biên | Điểm đúng 8.0 | `[8.0]` → 1 |
| Biên | Điểm 7.99 | `[7.99]` → 0 |
| Ngoại lệ | Điểm âm | `[-1.0, -5.0, 9.0]` → 1 |
| Ngoại lệ | Điểm > 10 | `[11.0, 15.0, 8.5]` → 1 |
| Ngoại lệ | Tất cả không hợp lệ | `[-1.0, 11.0]` → 0 |
| Ngoại lệ | Null list | `null` → 0 |

### calculateValidAverage()

| Loại | Test Case | Mô tả |
|------|-----------|-------|
| Bình thường | Điểm hợp lệ + không hợp lệ | `[9.0, 8.5, 7.0, 11.0, -1.0]` → 8.17 |
| Bình thường | Toàn bộ hợp lệ | `[7.0, 8.0, 9.0]` → 8.0 |
| Biên | Danh sách trống | `[]` → 0 |
| Biên | Chỉ chứa 0 | `[0.0]` → 0.0 |
| Biên | Chỉ chứa 10 | `[10.0]` → 10.0 |
| Biên | Giá trị 0 và 10 | `[0.0, 10.0]` → 5.0 |
| Ngoại lệ | Tất cả không hợp lệ | `[-1.0, 11.0]` → 0 |
| Ngoại lệ | Null list | `null` → 0 |

## Phương pháp kiểm thử

- **Phân vùng tương đương (Equivalence Partitioning)**: Chia dữ liệu thành nhóm hợp lệ (0-10) và không hợp lệ (<0, >10)
- **Phân tích giá trị biên (Boundary Value Analysis)**: Test tại các biên 0, 8.0, 10 và các giá trị lân cận
- **Kiểm thử ngoại lệ (Exception Testing)**: Xử lý null, danh sách rỗng, phần tử null

## Cách chạy chương trình

### Yêu cầu
- Java JDK 17 trở lên
- Apache Maven 3.8+

### Chạy test
```bash
cd Task_1/unit-test
mvn test
```

### Kết quả mong đợi
Tất cả 28 test cases phải PASS thành công.

## Tài liệu tham khảo
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
