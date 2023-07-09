package com.jhlee.rongame.data.local

import com.jhlee.rongame.R
import com.jhlee.rongame.common.constants.TypeConst
import com.jhlee.rongame.common.constants.TypeConst.Companion.ELEMENTAL
import com.jhlee.rongame.domain.model.Hero

object InitData {
    var heroList: List<Hero> = arrayListOf<Hero>().apply {
        this.add(Hero(0, ELEMENTAL, "불", R.drawable.ic_fire.toString()))
        this.add(Hero(0, ELEMENTAL, "물", R.drawable.ic_water_drop.toString()))
        this.add(Hero(0, ELEMENTAL, "풀", R.drawable.ic_grass.toString()))
        this.add(Hero(0, ELEMENTAL, "철", R.drawable.ic_iron.toString()))
        this.add(Hero(0, TypeConst.ANIMAL, "벌레", R.drawable.ic_bug.toString()))

    }
}