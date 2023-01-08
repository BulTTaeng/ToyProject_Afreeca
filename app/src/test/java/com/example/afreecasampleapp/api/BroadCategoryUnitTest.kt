package com.example.afreecasampleapp.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.afreecasampleapp.data.repository.AfreecaTvRepository
import com.example.afreecasampleapp.viewmodels.AfreecaTvViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import java.util.*
import kotlin.collections.ArrayList

@RunWith(AndroidJUnit4::class)
class BroadCategoryUnitTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    val service = AfreecaTvApiService.create()
    lateinit var repository: AfreecaTvRepository
    lateinit var viewModel: AfreecaTvViewModel

    @Before
    fun setUp() {
        repository = AfreecaTvRepository(service)
        viewModel = AfreecaTvViewModel(repository)
    }

    @Test
    fun initValueCheck() {
        assertEquals(viewModel.currentCategoryId, -1)
        assertEquals(viewModel.currentBroadLists.size, 3)
    }

    @Test
    fun notEmptyCategories() = runTest {
        val broadList = repository.getCategories().toList()
        for (it in broadList[0]) {
            assert(it.cate_no.toString().isNotEmpty())
        }
    }

    @Test
    fun correctCategoriesBroadList() = runTest {

        val categoryNumber = ArrayList<Int>()
        val categoryList = repository.getCategories().toList()
        val categories = categoryList[0]
        for (it in categories) {
            categoryNumber.add(it.cate_no)
        }
        assert(categoryNumber.isNotEmpty())

        val idx = Random().nextInt(categoryNumber.size)
        val selectedCategoryNum = categoryNumber[idx]

        val broadList = service.broadList(pageNo = 1, select_value = selectedCategoryNum)
        if(broadList.broad.isNotEmpty()) assertEquals(broadList.broad.get(0).broad_cate_no ,selectedCategoryNum )

    }

}