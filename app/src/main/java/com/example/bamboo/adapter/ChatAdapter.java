package com.example.bamboo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bamboo.R;
import com.example.bamboo.model.entity.Chat;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ChatAdapter extends FirestoreRecyclerAdapter<Chat, ChatAdapter.ViewHolder> {

String sender;
    FirestoreRecyclerOptions<Chat> options;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ChatAdapter(@NonNull FirestoreRecyclerOptions<Chat> options) {
        super(options);
        this.options=options;
    }



    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Chat chat) {
        sender=chat.getSender();
        if (chat.getSender().equals("user")){
            holder.msgUser.setVisibility(View.VISIBLE);
            holder.msgUser.setText(chat.getMessage());
            holder.msgBranch.setVisibility(View.GONE);
        }else if(chat.getSender().equals("branch")){
            holder.msgBranch.setVisibility(View.VISIBLE);
            holder.msgBranch.setText(chat.getMessage());
            holder.msgUser.setVisibility(View.GONE);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false))  ;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView msgUser,msgBranch;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            msgUser= itemView.findViewById(R.id.message_user);
            msgBranch= itemView.findViewById(R.id.message_branch);

        }
    }
}
