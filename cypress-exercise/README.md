# Task 2: Kiểm thử tự động End-to-End với Cypress

## Mục tiêu

Hiểu và thực hành các kịch bản kiểm thử tự động end-to-end phổ biến bằng cách sử dụng Cypress để kiểm tra trang web mẫu [saucedemo.com](https://www.saucedemo.com).

## Cấu trúc thư mục

```
Task_2/
└── cypress-exercise/
    ├── package.json               # npm project config
    ├── cypress.config.js          # Cấu hình Cypress
    └── cypress/
        └── e2e/
            ├── login_spec.cy.js   # Test đăng nhập (6 test cases)
            ├── cart_spec.cy.js    # Test giỏ hàng (7 test cases)
            └── checkout_spec.cy.js # Test thanh toán (4 test cases)
```

## Kịch bản kiểm thử

### 1. Login Test (`login_spec.cy.js`)

| # | Test Case | Mô tả |
|---|-----------|-------|
| 1 | Đăng nhập thành công | `standard_user` / `secret_sauce` → URL chứa `/inventory.html` |
| 2 | Đăng nhập thất bại | `invalid_user` / `wrong_password` → Thông báo lỗi |
| 3 | Username trống | Chỉ nhập password → "Username is required" |
| 4 | Password trống | Chỉ nhập username → "Password is required" |
| 5 | Cả 2 trống | Không nhập gì → "Username is required" |
| 6 | User bị khóa | `locked_out_user` → Thông báo "locked out" |

### 2. Cart Test (`cart_spec.cy.js`)

| # | Test Case | Mô tả |
|---|-----------|-------|
| 1 | Thêm sản phẩm | Click "Add to cart" → Badge hiển thị "1" |
| 2 | Xóa sản phẩm | Click "Remove" → Badge biến mất |
| 3 | Sắp xếp giá thấp→cao | Sort "Price (low to high)" → Sản phẩm đầu tiên $7.99 |
| 4 | Thêm nhiều sản phẩm | Thêm 3 sản phẩm → Badge hiển thị "3" |
| 5 | Sắp xếp giá cao→thấp | Sort "Price (high to low)" → Sản phẩm đầu tiên $49.99 |
| 6 | Sắp xếp A-Z | Sort by name A-Z → Verify thứ tự |
| 7 | Xem giỏ hàng | Click cart icon → URL `/cart.html` + có sản phẩm |

### 3. Checkout Test (`checkout_spec.cy.js`)

| # | Test Case | Mô tả |
|---|-----------|-------|
| 1 | Thanh toán đầy đủ | Thêm SP → Checkout → Điền info → Continue → `/checkout-step-two.html` |
| 2 | Thông tin thiếu | Continue mà không điền → Thông báo lỗi |
| 3 | Hoàn thành đơn hàng | Full flow → Finish → "Thank you for your order" |
| 4 | Tổng kết đơn hàng | Step two → Hiển thị sản phẩm + tổng tiền |

## Phương pháp kiểm thử

- **Happy Path**: Luồng chính hoạt động đúng (đăng nhập → thêm SP → thanh toán)
- **Negative Testing**: Đầu vào không hợp lệ (thông tin sai, trường trống)
- **Boundary Testing**: Trạng thái biên (giỏ hàng trống, user bị khóa)
- **UI Verification**: Kiểm tra URL redirect, badge hiển thị, thông báo lỗi

## Cách chạy

### Yêu cầu
- Node.js phiên bản 14 trở lên
- npm

### Cài đặt
```bash
cd Task_2/cypress-exercise
npm install
```

### Chạy test (GUI)
```bash
npx cypress open
```

### Chạy test (Headless - CLI)
```bash
npx cypress run
```

## Tài liệu tham khảo
- [Cypress Documentation](https://docs.cypress.io)
- [SauceDemo](https://www.saucedemo.com)
