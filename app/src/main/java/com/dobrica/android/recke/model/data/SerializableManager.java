package com.dobrica.android.recke.model.data;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableManager {

    /**
     * Saves a serializable object.
     *
     * @param <GameModel> The type of the object.
     * @param context The application context.
     * @param objectToSave The object to save.
     * @param MyModels The name of the file.
     */

    public static <GameModel extends Serializable> void saveSerializable(Context context, GameModel objectToSave, String MyModels) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(MyModels, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(objectToSave);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a serializable object.
     *
     * @param context The application context.
     * @param MyModels The filename.
     * @param <GameModel> The object type.
     *
     * @return the serializable object.
     */

    public static<GameModel extends Serializable> GameModel readSerializable(Context context, String MyModels) {
        GameModel objectToReturn = null;

        try {
            FileInputStream fileInputStream = context.openFileInput(MyModels);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectToReturn = (GameModel) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objectToReturn;
    }
    public static <ArrayList extends Serializable> void saveArrayList(Context context, ArrayList objectToSave, String historyFileName) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(historyFileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(objectToSave);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a serializable object.
     *
     * @param context The application context.
     * @param historyFileName The filename.
     * @param <ArrayList> The object type.
     *
     * @return the serializable object.
     */

    public static<ArrayList extends Serializable> ArrayList readArrayList(Context context, String historyFileName) {
        ArrayList objectToReturn = null;

        try {
            FileInputStream fileInputStream = context.openFileInput(historyFileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectToReturn = (ArrayList) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }

        return objectToReturn;
    }

    /**
     * Removes a specified file.
     *
     * @param context The application context.
     * @param filename The name of the file.
     */

    public static void removeSerializable(Context context, String filename) {
        context.deleteFile(filename);
    }

}