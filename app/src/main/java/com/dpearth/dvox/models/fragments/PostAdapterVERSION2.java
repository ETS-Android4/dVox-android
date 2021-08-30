package com.dpearth.dvox.models.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpearth.dvox.R;
import com.dpearth.dvox.smartcontract.Post;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PostAdapterVERSION2 extends RecyclerView.Adapter<PostAdapterVERSION2.PostHolder> {
    private Context context;
    private List<Post> posts;

    public PostAdapterVERSION2() {

    }

    public PostAdapterVERSION2(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_post_version2, parent, false);
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
        notifyDataSetChanged();//We will change the method later
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
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

            avatar.setImageResource(imageResource);
        }

        public String stringToAvatar(String username){
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
