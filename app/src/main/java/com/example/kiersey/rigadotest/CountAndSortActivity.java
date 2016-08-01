package com.example.kiersey.rigadotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class CountAndSortActivity extends AppCompatActivity {

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_and_sort);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void Run(View view) {
        main();
    }

    public void main() {
        int[] numbers = getNumbers();
        runTest(numbers);
    }

    private static int[] getNumbers() {
        int count = (int) (Math.random() * 500) + 500; // generate between 500 and 1,000 numbers
        int numbers[] = new int[count];

        for (int c = 0; c < count; ++c) {
            // each number is from 1 to 20, weighted more towards 20
            numbers[c] = (int) (Math.sin(Math.random() * Math.PI) * 20) + 1;
        }

        System.out.println("\n" + count + " total numbers\n");

        return numbers;
    }

    void runTest(int number[]) {
        // print the numbers sorted by the count of each unique number
        int len = number.length;
        int counter;
        node head = null; //initialize head for BST

        TextView myText = (TextView) findViewById(R.id.textView);
        myText.setText("Frequency list:\n");

        for (int i = 1; i < 21; ++i) {
            counter = 0;

            //count through the list of possible numbers, adding them to counter when found
            for (int j = 0; j < len; ++j) {
                if(number[j] == i)
                    ++counter;
            }

            //add data to BST
            if(head == null)
                head = new node(i, counter);
            else
                nodeInsert(head,i,counter);
        }
        treePrint(head);

    }

    //adds a node to the BST, id is the number in question, value is the freq of that number
    private void nodeInsert(node focusNode, int id, int value) {
        //greater than or equals to the right
        if(value >= focusNode.value)
            if(focusNode.right == null)
                focusNode.right = new node(id,value);
            else
                nodeInsert(focusNode.right, id, value);
        //less than to the left
        else
            if(focusNode.left == null)
                focusNode.left = new node(id,value);
            else
                nodeInsert(focusNode.left, id, value);
    }

    //prints the tree in the form "id * frequency"
    private void treePrint(node head) {
        TextView myText = (TextView) findViewById(R.id.textView);
        if(head == null)
            return;
        treePrint(head.right);
        myText.append(head.id + " * " + head.value + "\n");
        treePrint(head.left);
    }

}
