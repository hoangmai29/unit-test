/**
 * Checkout Test - Kiểm thử quy trình thanh toán
 * Trang web: https://www.saucedemo.com
 *
 * Kịch bản: Đăng nhập → Thêm sản phẩm → Thanh toán → Xác minh
 */
describe('Checkout Test', () => {

    beforeEach(() => {
        // Đăng nhập trước mỗi test
        cy.visit('https://www.saucedemo.com');
        cy.get('#user-name').type('standard_user');
        cy.get('#password').type('secret_sauce');
        cy.get('#login-button').click();
    });

    // ==========================================
    // Kịch bản chính: Quy trình thanh toán đầy đủ
    // ==========================================

    it('Should complete the checkout process', () => {
        // Bước 1: Thêm sản phẩm vào giỏ hàng
        cy.get('.inventory_item').first().find('.btn_inventory').click();
        cy.get('.shopping_cart_badge').should('have.text', '1');

        // Bước 2: Đi đến giỏ hàng
        cy.get('.shopping_cart_link').click();
        cy.url().should('include', '/cart.html');

        // Bước 3: Nhấn Checkout
        cy.get('[data-test="checkout"]').click();

        // Bước 4: Điền thông tin thanh toán
        cy.get('[data-test="firstName"]').type('John');
        cy.get('[data-test="lastName"]').type('Doe');
        cy.get('[data-test="postalCode"]').type('12345');

        // Bước 5: Nhấn Continue
        cy.get('[data-test="continue"]').click();

        // Bước 6: Xác minh chuyển đến trang xác nhận thanh toán
        cy.url().should('include', '/checkout-step-two.html');
    });

    // ==========================================
    // Các test bổ sung
    // ==========================================

    it('Should show error when checkout info is incomplete', () => {
        // Thêm sản phẩm và vào giỏ hàng
        cy.get('.inventory_item').first().find('.btn_inventory').click();
        cy.get('.shopping_cart_link').click();
        cy.get('[data-test="checkout"]').click();

        // Nhấn Continue mà không điền thông tin
        cy.get('[data-test="continue"]').click();

        // Xác minh thông báo lỗi
        cy.get('.error-message-container').should('be.visible');
    });

    it('Should complete full checkout and see confirmation', () => {
        // Thêm sản phẩm
        cy.get('.inventory_item').first().find('.btn_inventory').click();

        // Đi đến giỏ hàng
        cy.get('.shopping_cart_link').click();

        // Checkout
        cy.get('[data-test="checkout"]').click();

        // Điền thông tin
        cy.get('[data-test="firstName"]').type('John');
        cy.get('[data-test="lastName"]').type('Doe');
        cy.get('[data-test="postalCode"]').type('12345');
        cy.get('[data-test="continue"]').click();

        // Nhấn Finish
        cy.get('[data-test="finish"]').click();

        // Xác minh trang hoàn thành
        cy.url().should('include', '/checkout-complete.html');
        cy.get('.complete-header').should('contain', 'Thank you for your order');
    });

    it('Should display order summary on checkout step two', () => {
        // Thêm sản phẩm
        cy.get('.inventory_item').first().find('.btn_inventory').click();

        // Đi đến thanh toán
        cy.get('.shopping_cart_link').click();
        cy.get('[data-test="checkout"]').click();

        // Điền thông tin
        cy.get('[data-test="firstName"]').type('John');
        cy.get('[data-test="lastName"]').type('Doe');
        cy.get('[data-test="postalCode"]').type('12345');
        cy.get('[data-test="continue"]').click();

        // Xác minh trang tổng kết hiển thị đúng thông tin
        cy.get('.cart_item').should('have.length', 1);
        cy.get('.summary_total_label').should('be.visible');
    });
});
