package com.dpearth.dvox.models.recycleviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpearth.dvox.R;
import com.dpearth.dvox.smartcontract.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapterVERSION2 extends RecyclerView.Adapter<PostAdapterVERSION2.PostHolder> {

    private List<Post> posts = new ArrayList<>();
    private ViewGroup mainActivity;


    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post_version2, parent, false);
        this.mainActivity = parent;
        return new PostHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post currentPost = posts.get(position);
        holder.bind(currentPost);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
//        notifyDataSetChanged();//We will change the method later
    }

    class PostHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView author;
        private TextView message;
        private TextView hashtag;
        private TextView commentCount;
        private ImageView avatar;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.version2_title_text);
            author = itemView.findViewById(R.id.version2_author_text);
            message = itemView.findViewById(R.id.version2_message_text);
            hashtag = itemView.findViewById(R.id.version2_hashtag_text);
            commentCount = itemView.findViewById(R.id.version2_comment_number);
            avatar = itemView.findViewById(R.id.version2_avatar_image);
        }

        private void bind(Post post) {
            //Assign values to UI elements
            title.setText(post.getTitle());
            author.setText(post.getAuthor());
            message.setText(post.getMessage());
            hashtag.setText(post.getHashtag());
            commentCount.setText(String.valueOf(post.getCommentCount()));


            String uri = "drawable/" + stringToAvatar(post.getAuthor()).toLowerCase();
            int imageResource = mainActivity.getContext().getResources().getIdentifier(uri, null, mainActivity.getContext().getPackageName());

            avatar.setImageResource(imageResource);
        }

        private String stringToAvatar(String username){
            String[] array = username.split("_");

            if (array.length == 3){
                return array[1];
            } else if (array.length == 4){
                return array[1] + "_" + array[2];
            } else {
                return "Hacker";
            }
        }

    }
}
