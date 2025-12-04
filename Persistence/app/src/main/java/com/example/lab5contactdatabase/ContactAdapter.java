package com.example.lab5contactdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.contactName.setText(contact.getName());
        holder.contactDob.setText("DoB: " + contact.getDob());
        holder.contactEmail.setText("Email: " + contact.getEmail());
        holder.contactAvatar.setImageResource(contact.getAvatarResId());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        ImageView contactAvatar;
        TextView contactName;
        TextView contactDob;
        TextView contactEmail;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            contactAvatar = itemView.findViewById(R.id.contactAvatar);
            contactName = itemView.findViewById(R.id.contactName);
            contactDob = itemView.findViewById(R.id.contactDob);
            contactEmail = itemView.findViewById(R.id.contactEmail);
        }
    }
}