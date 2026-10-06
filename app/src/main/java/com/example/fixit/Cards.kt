package com.example.fixit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fixit.screens.BalanceCard
import com.example.fixit.ui.theme.FixitTheme

// ---------- Shared selectable card ----------
@Composable
private fun SelectableCard(
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    Card(
        onClick = onClick,
        modifier = modifier.semantics { selected = isSelected },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) colors.primary else colors.outline
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) colors.primary.copy(alpha = 0.08f) else colors.surface,
            contentColor = colors.onSurface
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        content()
    }
}

// ---------- Balance Card ----------
@Composable
fun BalanceCard(
    balance: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SelectableCard(isSelected = isSelected, onClick = onClick, modifier = modifier) {
        Text(
            text = stringResource(R.string.balance_label, balance),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = ConseFontFamily,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 70.dp)
                .padding(16.dp)
        )
    }
}

// ---------- Data Plan Card ----------
@Composable
fun DataPlanCard(
    plan: DataPlan,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SelectableCard(isSelected = isSelected, onClick = onClick, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 80.dp)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = plan.dataAmount,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = ConseFontFamily
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.AccessTime,
                        contentDescription = null, // text beside it already describes it
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = validityText(plan),
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = ConseFontFamily,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = stringResource(R.string.price_format, plan.price),
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = ConseFontFamily,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun validityText(plan: DataPlan): String = when (plan.validityUnit) {
    ValidityUnit.DAYS -> pluralStringResource(R.plurals.validity_days, plan.validityValue, plan.validityValue)
    ValidityUnit.HOURS -> pluralStringResource(R.plurals.validity_hours, plan.validityValue, plan.validityValue)
}

@Preview(showBackground = true)
@Composable
private fun CardsPreview() {
    FixitTheme(dynamicColor = false) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            BalanceCard(balance = 1000, isSelected = true, onClick = {})
            DataPlanCard(
                plan = DataPlan(1, "30 GB", 30, ValidityUnit.DAYS, 299),
                isSelected = false,
                onClick = {}
            )
        }
    }
}
