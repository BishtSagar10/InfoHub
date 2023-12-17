package com.example.infohub.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.infohub.R
import com.example.infohub.domain.model.Article
import com.example.infohub.domain.model.Source
import com.example.infohub.presentation.onboarding.dimens.ArticleCardSize
import com.example.infohub.presentation.onboarding.dimens.ExtraSmallPadding
import com.example.infohub.presentation.onboarding.dimens.ExtraSmallPadding2
import com.example.infohub.presentation.onboarding.dimens.SmallIconSize
import com.example.infohub.ui.theme.InfoHubTheme

@Composable
fun ArticleCard(
    modifier: Modifier=Modifier,
    article: Article,
    onClick:()-> Unit
){
    val context= LocalContext.current

    Row (modifier=modifier.clickable { onClick() }){


        AsyncImage(
            modifier= Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier= Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(
                    ArticleCardSize
                )
        ) {
            Text(
                text = article.title,
                style=MaterialTheme
                    .typography
                    .bodyMedium,
                color= colorResource(
                    id = R.color.black
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(
                    text=article.source.name,
                    style=MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color= colorResource(id = R.color.black)
                )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Icon(
                    painter= painterResource(id =R.drawable.ic_time ),contentDescription=null,
                    modifier=Modifier.size(SmallIconSize),
                    //tint= colorResource(id = R.color.black)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(
                    text=article.publishedAt,
                    style=MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color= colorResource(id = R.color.black)
                )
            }
        }
    }
}


@Preview(showBackground = true)

@Composable
fun ArticleCardPreview(){
    InfoHubTheme{
        ArticleCard(article = Article(
            author = "",
            content = "",
            description = "",
            publishedAt = "2 hours",
            source= Source(id="",name="BBC"),
            title="HER traint broke down. my name is sagar bisht .what is your name",
            url="",
            urlToImage = ""
        )
        ){}
    }
}