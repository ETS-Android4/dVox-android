package com.dpearth.dvox.models.recycleviews;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpearth.dvox.R;
import com.dpearth.dvox.smartcontract.Comment;
import com.dpearth.dvox.smartcontract.Post;
import com.dpearth.dvox.smartcontract.SmartContract;

import java.util.Collections;
import java.util.List;

//Todo: Go to comment view when post card is pressed
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>   {

    private List<Comment> comments;

    public boolean shimmer = true;

    private Context context;
    private int postId;

    public CommentAdapter(List<Comment> comments, Context context, int postId){
        this.comments = Collections.unmodifiableList(comments);
        this.context = context;
        this.postId = postId;
    }

    @Override
    public int getItemViewType(int position) {
        if (comments.get(position).getCommentAuthor().equals("███████"))
            return 1;
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        View shimmerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_shimmer, parent, false);

        switch(viewType){
            case 0:
                return new ViewHolder(view);
            case 1:
                return new ViewHolder(shimmerView);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setPosts(List<Post> posts) {
        this.comments = comments;
        notifyDataSetChanged();//We will change the method later
    }

    public Comment getCommentsAt(int position){
        return comments.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAuthor;
        private TextView tvMessage;
        private ImageView tvAvatar;
        private ImageView commentToBanIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Get all UI (view) elements
            tvAuthor = itemView.findViewById(R.id.comment_author);
            tvMessage = itemView.findViewById(R.id.comment_message);
            tvAvatar = itemView.findViewById(R.id.comment_post_avatar);
            commentToBanIcon = itemView.findViewById(R.id.comment_post_avatar);
        }

        public void bind(Comment comment) {
            //Assign values to UI elements
            tvAuthor.setText(comment.getCommentAuthor());
            tvMessage.setText(comment.getCommentMessage());

            String uri = "drawable/" + stringToAvatar(comment.getCommentAuthor()).toLowerCase();

            if (uri.equals("drawable/hacker") && comment.getCommentAuthor().equals("███████"))
                uri = "drawable/black_square";

            int imageResource = itemView.getContext().getResources().getIdentifier(uri, null, itemView.getContext().getPackageName());

            tvAvatar.setImageResource(imageResource);

            commentToBanIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    banComment(postId, comment.getId().intValue());
                    Toast.makeText(context, "The Comment Is Getting Banned", Toast.LENGTH_SHORT);
                }
            });
        }


        public void banComment(int postId, int commentId){

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences preferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE);

                    while (preferences.getString("credentials", "error").equals("error") ||
                            preferences.getString("contractAddress", "error").equals("error") ||
                            preferences.getString("credentials", "error").equals("error")) {
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    SmartContract contract = new SmartContract(preferences);

                    contract.banComment(postId, commentId);
                    Log.d("BANNING COMMENTS", ": ------------------------------------------------------------");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                    }
//                });
                }

            });

            thread.start();

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
