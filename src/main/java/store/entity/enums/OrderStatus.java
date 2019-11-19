package store.entity.enums;

public enum OrderStatus {
    processing("Обработка"),
    delivered("Отправлен"),
    canceled("Отменен");

    private String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
