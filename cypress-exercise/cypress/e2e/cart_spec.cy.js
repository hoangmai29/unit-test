/**
 * Cart Test - Kiểm thử chức năng giỏ hàng
 * Trang web: https://www.saucedemo.com
 *
 * Kịch bản 3: Thêm sản phẩm vào giỏ hàng
 * Kịch bản thêm: Xóa sản phẩm khỏi giỏ hàng
 * Kịch bản 4: Sắp xếp sản phẩm theo giá (low to high)
 */
describe('Cart Test', () => {

    beforeEach(() => {
        // Đăng nhập trước mỗi test
        cy.visit('https://www.saucedemo.com');
        cy.get('#user-name').type('standard_user');
        cy.get('#password').type('secret_sauce');
        cy.get('#login-button').click();
    });

    // ==========================================
    // Kịch bản 3: Thêm sản phẩm vào giỏ hàng
    // ==========================================

    it('Should add a product to the cart', () => {
        // Bước 1: Nhấn nút "Add to cart" của sản phẩm đầu tiên
        cy.get('.inventory_item').first().find('.btn_inventory').click();

        // Bước 2: Xác minh số lượng sản phẩm trong giỏ hàng là 1
        cy.get('.shopping_cart_badge').should('have.text', '1');
    });

    // ==========================================
    // Kịch bản thêm: Xóa sản phẩm khỏi giỏ hàng
    // ==========================================

    it('Should remove a product from the cart', () => {
        // Bước 1: Thêm sản phẩm vào giỏ hàng
        cy.get('.inventory_item').first().find('.btn_inventory').click();

        // Xác minh đã thêm thành công
        cy.get('.shopping_cart_badge').should('have.text', '1');

        // Bước 2: Nhấn nút "Remove" để xóa sản phẩm
        cy.get('.inventory_item').first().find('.btn_inventory').click();

        // Bước 3: Xác minh giỏ hàng trở về 0 (badge biến mất)
        cy.get('.shopping_cart_badge').should('not.exist');
    });

    // ==========================================
    // Kịch bản 4: Sắp xếp sản phẩm theo giá
    // ==========================================

    it('Should sort products by price low to high', () => {
        // Bước 1: Chọn bộ lọc "Price (low to high)" từ dropdown
        cy.get('.product_sort_container').select('lohi');

        // Bước 2: Xác minh sản phẩm đầu tiên có giá thấp nhất
        cy.get('.inventory_item_price').first().should('have.text', '$7.99');
    });

    // ==========================================
    // Các test bổ sung cho kiểm thử kỹ lưỡng
    // ==========================================

    it('Should add multiple products to the cart', () => {
        // Thêm 3 sản phẩm đầu tiên vào giỏ hàng
        cy.get('.inventory_item').eq(0).find('.btn_inventory').click();
        cy.get('.inventory_item').eq(1).find('.btn_inventory').click();
        cy.get('.inventory_item').eq(2).find('.btn_inventory').click();

        // Xác minh số lượng sản phẩm trong giỏ hàng là 3
        cy.get('.shopping_cart_badge').should('have.text', '3');
    });

    it('Should sort products by price high to low', () => {
        // Chọn bộ lọc "Price (high to low)"
        cy.get('.product_sort_container').select('hilo');

        // Xác minh sản phẩm đầu tiên có giá cao nhất
        cy.get('.inventory_item_price').first().should('have.text', '$49.99');
    });

    it('Should sort products by name A to Z', () => {
        // Chọn sắp xếp theo tên A-Z
        cy.get('.product_sort_container').select('az');

        // Xác minh sản phẩm đầu tiên bắt đầu bằng A hoặc B
        cy.get('.inventory_item_name').first().invoke('text').then((text) => {
            expect(text.trim()).to.match(/^[A-B]/);
        });
    });

    it('Should navigate to cart page and see added items', () => {
        // Thêm sản phẩm
        cy.get('.inventory_item').first().find('.btn_inventory').click();

        // Click vào biểu tượng giỏ hàng
        cy.get('.shopping_cart_link').click();

        // Xác minh đã chuyển đến trang giỏ hàng
        cy.url().should('include', '/cart.html');

        // Xác minh có 1 sản phẩm trong giỏ hàng
        cy.get('.cart_item').should('have.length', 1);
    });
});
