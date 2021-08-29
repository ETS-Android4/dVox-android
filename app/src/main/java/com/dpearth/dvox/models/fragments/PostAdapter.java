package com.dpearth.dvox.models.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpearth.dvox.R;
import com.dpearth.dvox.smartcontract.Post;

import org.web3j.abi.datatypes.Array;
import org.web3j.abi.datatypes.primitive.Char;

import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>  {

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvMessage;
        private TextView tvHashtag;
        private TextView tvCommentNumber;
        private ImageView tvAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Get all UI (view) elements
            tvTitle = itemView.findViewById(R.id.title_text);
            tvAuthor = itemView.findViewById(R.id.author_text);
            tvMessage = itemView.findViewById(R.id.message_text);
            tvHashtag = itemView.findViewById(R.id.hashtag_text);
            tvCommentNumber = itemView.findViewById(R.id.comment_number);
            tvAvatar = itemView.findViewById(R.id.avatar_image);
        }

        public void bind(Post post) {
            //Assign values to UI elements
            tvTitle.setText(post.getTitle());
            tvAuthor.setText(post.getAuthor());
            tvMessage.setText(post.getMessage());
            tvHashtag.setText(post.getHashtag());
            tvCommentNumber.setText(String.valueOf(post.getCommentCount()));


            String uri = "drawable/" + stringToAvatar(post.getAuthor()).toLowerCase();
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

            tvAvatar.setImageResource(imageResource);
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
