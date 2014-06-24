package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.PostEntity;


public class PostEditHandlerForGet extends BaseHandler {
    @Override
    public Object handle() {

        String postId = request.params(":id");
        if (postId != null) {
            PostEntity post = (PostEntity)database.createQuery("From PostEntity WHERE id =?")
                    .setString(0, postId).uniqueResult();
            if (!post.getAuthor().equals(currentUser)) {
                return showMessage("您不能修改他人发表的文章");
            }
            modal.put("post", post);
        }
        return rendered();

    }
    @Override
    public BaseHandler acceptGet(String path) {
        super.acceptGet(path);
        super.acceptGet(path + "/:id");
        return this;
    }
}
