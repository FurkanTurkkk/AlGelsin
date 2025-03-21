package org.AlGelsin;

public class IncreaseStockEventDto {

    private CartDto cartDto;
    private Long authId;

    public IncreaseStockEventDto() {
    }

    public IncreaseStockEventDto(CartDto cartDto, Long authId) {
        this.cartDto = cartDto;
        this.authId = authId;
    }

    public CartDto getCartDto() {
        return cartDto;
    }

    public void setCartDto(CartDto cartDto) {
        this.cartDto = cartDto;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }
}
