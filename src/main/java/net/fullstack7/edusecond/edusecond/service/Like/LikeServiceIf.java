package net.fullstack7.edusecond.edusecond.service.Like;

public interface LikeServiceIf {

    public void insertLike(String userId, Integer productId);
    public void deleteLike(Integer wishId);

    public void getLike(Integer productId, String userId);
}
