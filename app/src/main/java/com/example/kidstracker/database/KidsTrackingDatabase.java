package com.example.kidstracker.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.kidstracker.database.dao.ChildDao;
import com.example.kidstracker.database.dao.EventDao;
import com.example.kidstracker.database.dao.MedicalQuestionDao;
import com.example.kidstracker.database.dao.NoteDao;
import com.example.kidstracker.database.dao.RoutineScreeningQuestionDao;
import com.example.kidstracker.database.dao.UserDao;
import com.example.kidstracker.models.Child;
import com.example.kidstracker.models.Events;
import com.example.kidstracker.models.MedicalQuestion;
import com.example.kidstracker.models.Note;
import com.example.kidstracker.models.RoutineScreeningQuestion;
import com.example.kidstracker.models.User;

@Database(entities = {Note.class, Events.class, User.class, MedicalQuestion.class, RoutineScreeningQuestion.class, Child.class}, version = 8)
public abstract class KidsTrackingDatabase extends RoomDatabase {

    private static KidsTrackingDatabase instance;

    public abstract NoteDao noteDao();
    public abstract EventDao eventDao();
    public abstract UserDao userDao();
    public abstract MedicalQuestionDao medicalQuestionDao();
    public abstract RoutineScreeningQuestionDao routineScreeningQuestionDao();
    public abstract ChildDao childDao();

    public static synchronized KidsTrackingDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    KidsTrackingDatabase.class, "kids_tracking_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    MedicalQuestionDao medicalQuestionDao = getInstance(context).medicalQuestionDao();
                                    medicalQuestionDao.insertMedicalQuestion(new MedicalQuestion("Has your child had any prior surgeries?", "Yes", "No", "I don't know"));
                                    medicalQuestionDao.insertMedicalQuestion(new MedicalQuestion("Has your child ever been diagnosed with eye or vision problems?", "Yes", "No", "I don't know"));
                                    medicalQuestionDao.insertMedicalQuestion(new MedicalQuestion("Has your child ever been diagnosed with ear or hearing problems?", "Yes", "No", "I don't know"));
                                    medicalQuestionDao.insertMedicalQuestion(new MedicalQuestion("Has your child ever been diagnosed with neck or cervical spine (C-spine) problems?", "Yes", "No", "I don't know"));
                                    medicalQuestionDao.insertMedicalQuestion(new MedicalQuestion("Has your child ever been diagnosed with thyroid problems?", "Yes", "No", "I don't know"));
                                    medicalQuestionDao.insertMedicalQuestion(new MedicalQuestion("Has your child ever been diagnosed with heart problems?", "Yes", "No", "I don't know"));
                                    medicalQuestionDao.insertMedicalQuestion(new MedicalQuestion("Has your child ever been diagnosed with breathing problems?", "Yes", "No", "I don't know"));
                                    medicalQuestionDao.insertMedicalQuestion(new MedicalQuestion("Has your child ever been diagnosed with stomach or intestinal problems?", "Yes", "No", "I don't know"));
                                    medicalQuestionDao.insertMedicalQuestion(new MedicalQuestion("Has your child ever been diagnosed with problems related to sleep?", "Yes", "No", "I don't know"));
                                    medicalQuestionDao.insertMedicalQuestion(new MedicalQuestion("Has your child ever been diagnosed with problems related to the brain?", "Yes", "No", "I don't know"));
                                    medicalQuestionDao.insertMedicalQuestion(new MedicalQuestion("Has your child ever been diagnosed with problems related to the blood stream, read blood cells, white blood cells, platelets, or bone marrow?", "Yes", "No", "I don't know"));
                                    medicalQuestionDao.insertMedicalQuestion(new MedicalQuestion("Has your child ever been diagnosed with problems related to the immune system?", "Yes", "No", "I don't know"));

                                    RoutineScreeningQuestionDao routineScreeningQuestionDao = getInstance(context).routineScreeningQuestionDao();
                                    routineScreeningQuestionDao.insertRoutineScreeningQuestion(new RoutineScreeningQuestion("What is the date of your child's last well check up?", "Date", "I don't know"));
                                    routineScreeningQuestionDao.insertRoutineScreeningQuestion(new RoutineScreeningQuestion("What is the date of your child's next well check up?", "Date", "I don't know"));
                                    routineScreeningQuestionDao.insertRoutineScreeningQuestion(new RoutineScreeningQuestion("Is your child's hearing normal? (Were they able to pass their hearing test?)", "Date", "I don't know"));
                                    routineScreeningQuestionDao.insertRoutineScreeningQuestion(new RoutineScreeningQuestion("What is the date of your child's next hearing test?", "Date", "I don't know"));
                                    routineScreeningQuestionDao.insertRoutineScreeningQuestion(new RoutineScreeningQuestion("What is the date of your child's last eye exam?", "Date", "I don't know"));
                                    routineScreeningQuestionDao.insertRoutineScreeningQuestion(new RoutineScreeningQuestion("What is the date of your child's last blood test for low iron or anemia?", "Date", "I don't know"));
                                    routineScreeningQuestionDao.insertRoutineScreeningQuestion(new RoutineScreeningQuestion("What is the date of your child's last blood test for thyroid problems?", "Date", "I don't know"));

                                }
                            });
                        }
                    }).build();
        }
        return instance;
    }
}
