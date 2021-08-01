package com.example.mvvm82;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm82.databinding.ItemRecyclerBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder>{
    List<Contact> contacts=new ArrayList<>();

    private onItemClick onItemClick;
    interface onItemClick{
        void onItemClickListener(Contact contact);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                ,R.layout.item_recycler,parent,false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Contact contact=contacts.get(position);
        holder.binding.setContact(contact);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        private ItemRecyclerBinding binding;

        public Holder(@NonNull ItemRecyclerBinding binding) {
            super(binding.getRoot());

            this.binding=binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick!=null&&getAdapterPosition()!=RecyclerView.NO_POSITION){
                        onItemClick.onItemClickListener(contacts.get(getAdapterPosition()));
                    }
                }
            });
        }
    }
    public void setContacts(List<Contact> contacts){
        this.contacts=contacts;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(RecyclerAdapter.onItemClick onItemClick){
        this.onItemClick=onItemClick;
    }
    public Contact getContact(int position){
        return contacts.get(position);
    }
}
