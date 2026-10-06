package com.example.fixit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.fixit.ui.theme.FixitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FixitTheme(dynamicColor = false) {
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainScreen(
                        balance = 1000,
                        plans = samplePlans,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    balance: Int,
    plans: List<DataPlan>,
    modifier: Modifier = Modifier,
    onBuy: (DataPlan) -> Unit = {}
) {
    // rememberSaveable survives rotation / theme change. Store the id, not the object.
    var isBalanceSelected by rememberSaveable { mutableStateOf(false) }
    var selectedPlanId by rememberSaveable { mutableStateOf<Int?>(null) }
    val selectedPlan = plans.firstOrNull { it.id == selectedPlanId }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        BalanceCard(
            balance = balance,
            isSelected = isBalanceSelected,
            onClick = { isBalanceSelected = !isBalanceSelected },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.select_data_plan),
            style = MaterialTheme.typography.titleMedium,
            fontFamily = ConseFontFamily,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.semantics { heading() }
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(plans, key = { it.id }) { plan ->
                DataPlanCard(
                    plan = plan,
                    isSelected = selectedPlanId == plan.id,
                    onClick = {
                        selectedPlanId = if (selectedPlanId == plan.id) null else plan.id
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Button(
            onClick = { selectedPlan?.let(onBuy) },
            enabled = isBalanceSelected && selectedPlan != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(stringResource(R.string.buy_plan))
        }
    }
}
