package com.zg.burgerjoint.instrumentationtests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.persistence.BurgerJointDatabase
import com.zg.burgerjoint.persistence.daos.BurgerDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DatabaseTest {
    private lateinit var burgerDao : BurgerDao
    private lateinit var db: BurgerJointDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,BurgerJointDatabase::class.java
        ).build()
        burgerDao = db.getBurgerDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertIntoDatabaseTest() {
        val burgerOne = BurgerVO()
        burgerOne.burgerName = "Chicken Burger"
        burgerOne.burgerDescription = "" +
                "The hot chicken sandwich or simply \"hot chicken\" (Quebec French: sandwich chaud au poulet) is a type of" +
                "\n"+
                "Although less featured in other areas of North America, the sandwich is also found in different" +
                "\n"+
                "The sandwich was a working class dish already common and well established in North America" +
                "\n"+
                "This style of sandwich often makes use of leftovers from a previous meal. Substituting turkey"
        burgerOne.burgerImageUrl =
            "https:vignette.wikia.nocookie.net/simpsons-restaurants/images/2/20/Spicy_Cluker.png"
        burgerDao.insert(burgerOne)
        assert(burgerDao.findBurgerById(burgerOne.burgerId).value?.burgerId == burgerOne.burgerId)
    }
}