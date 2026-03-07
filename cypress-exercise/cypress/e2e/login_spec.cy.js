/**
 * Login Test - Kiểm thử chức năng đăng nhập
 * Trang web: https://www.saucedemo.com
 *
 * Kịch bản 1: Đăng nhập thành công với thông tin hợp lệ
 * Kịch bản 2: Đăng nhập thất bại với thông tin không hợp lệ
 */
describe('Login Test', () => {

    beforeEach(() => {
        // Truy cập trang chủ trước mỗi test
        cy.visit('https://www.saucedemo.com');
    });

    // ==========================================
    // Kịch bản 1: Đăng nhập thành công
    // ==========================================

    it('Should login successfully with valid credentials', () => {
        // Bước 1: Nhập tên người dùng hợp lệ
        cy.get('#user-name').type('standard_user');

        // Bước 2: Nhập mật khẩu hợp lệ
        cy.get('#password').type('secret_sauce');

        // Bước 3: Nhấn nút Login
        cy.get('#login-button').click();

        // Bước 4: Xác minh chuyển hướng đến trang sản phẩm
        cy.url().should('include', '/inventory.html');
    });

    // ==========================================
    // Kịch bản 2: Đăng nhập thất bại
    // ==========================================

    it('Should show error message with invalid credentials', () => {
        // Bước 1: Nhập tên người dùng không hợp lệ
        cy.get('#user-name').type('invalid_user');

        // Bước 2: Nhập mật khẩu sai
        cy.get('#password').type('wrong_password');

        // Bước 3: Nhấn nút Login
        cy.get('#login-button').click();

        // Bước 4: Xác minh thông báo lỗi hiển thị
        cy.get('.error-message-container').should('contain', 'Username and password do not match');
    });

    // ==========================================
    // Các test bổ sung cho kiểm thử kỹ lưỡng
    // ==========================================

    it('Should show error when username is empty', () => {
        // Chỉ nhập password, bỏ trống username
        cy.get('#password').type('secret_sauce');
        cy.get('#login-button').click();

        // Xác minh thông báo lỗi yêu cầu username
        cy.get('.error-message-container').should('contain', 'Username is required');
    });

    it('Should show error when password is empty', () => {
        // Chỉ nhập username, bỏ trống password
        cy.get('#user-name').type('standard_user');
        cy.get('#login-button').click();

        // Xác minh thông báo lỗi yêu cầu password
        cy.get('.error-message-container').should('contain', 'Password is required');
    });

    it('Should show error when both fields are empty', () => {
        // Không nhập gì, nhấn Login
        cy.get('#login-button').click();

        // Xác minh thông báo lỗi
        cy.get('.error-message-container').should('contain', 'Username is required');
    });

    it('Should login successfully with locked_out_user and show error', () => {
        // Thử đăng nhập với user bị khóa
        cy.get('#user-name').type('locked_out_user');
        cy.get('#password').type('secret_sauce');
        cy.get('#login-button').click();

        // User bị khóa sẽ hiển thị thông báo lỗi
        cy.get('.error-message-container').should('contain', 'locked out');
    });
});
