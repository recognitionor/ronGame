package com.jhlee.rongame.data.local

import com.jhlee.rongame.R
import com.jhlee.rongame.common.constants.TypeConst
import com.jhlee.rongame.common.constants.TypeConst.Companion.ELEMENTAL
import com.jhlee.rongame.data.local.entity.DBHero

object InitData {
    var heroList: List<DBHero> = arrayListOf<DBHero>().apply {
        this.add(DBHero(ELEMENTAL, "불", R.drawable.ic_fire.toString(), 3, 2, 1, 1, 1))
        this.add(DBHero(ELEMENTAL, "물", R.drawable.ic_water_drop.toString(), 2, 2, 1, 2, 1))
        this.add(DBHero(ELEMENTAL, "풀", R.drawable.ic_grass.toString(), 1, 3, 1, 2, 1))
        this.add(DBHero(ELEMENTAL, "철", R.drawable.ic_iron.toString(), 3, 2, 1, 2, 1))
        this.add(DBHero(TypeConst.ANIMAL, "벌레", R.drawable.ic_bug.toString(), 4, 1, 1, 1, 1))

    }
}