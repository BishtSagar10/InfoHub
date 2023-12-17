package com.example.infohub.presentation.details.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.infohub.R
import com.example.infohub.domain.model.Article
import com.example.infohub.domain.model.Source
import com.example.infohub.presentation.onboarding.dimens.ArticleImageHeight
import com.example.infohub.presentation.onboarding.dimens.MediumPadding1
import com.example.infohub.ui.theme.InfoHubTheme

@Composable
fun DetailsScreen(
    article: Article,
    event:(DetailsEvent) -> Unit,
    navigateUp:() -> Unit
){

    val context= LocalContext.current

    Column(
        modifier= Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also{
                    it.data= Uri.parse(article.url)
                    if(it.resolveActivity(context.packageManager)!=null){
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                           Intent(Intent.ACTION_SEND).also{
                               it.putExtra(Intent.EXTRA_TEXT,article.url)
                               it.type="text/plain"
                               if(it.resolveActivity(context.packageManager)!=null){
                                   context.startActivity(it)
                               }
                           }
            },
            onBookmarkClick = {event(DetailsEvent.UpsertDeleteArticle(article))},
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier=Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ){
            item {

                AsyncImage(
                    model = ImageRequest.Builder(context=context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier= Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale= ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(text=article
                    .title,
                    style=MaterialTheme
                        .typography
                        .titleLarge,
                    color= colorResource(
                        id = R.color.text_title
                    )
                )

                /*Text(text=article
                    .title,
                    style=MaterialTheme
                        .typography
                        .displaySmall,
                    color= colorResource(
                        id = R.color.text_title
                    )
                )*/

                Text(text=article.content,
                    style=MaterialTheme
                        .typography
                        .bodyLarge,
                    color= colorResource(
                        id = R.color.text_medium
                    )
                )
            }
        }

    }

}


@Preview(showBackground = true)
//@Preview(showBackground = true, uiMode =, )
@Composable
fun DetailsScreenPreview(){
    InfoHubTheme {
        DetailsScreen(
            article =Article(
                author="",
                title="s sunset nears, Raphaël De Cock leads a group of about 30 people through the Sonian Forest, just outside Brussels, Belgium. As they traverse the winding trail, he calls attention to the surrounding landscape. The forest, he tells them, is prime firefly habitat.",
                description="Artists are seeking ways to boost firefly conservation. They aren’t the only insects that could benefit",
                content="Such shows have become rarer in recent years, as many firefly species are in decline. Even so, it’s not easy to get funding to study them",
                publishedAt = "November 29, 2023120 (49) e2318525120",
                source = Source(
                    id="",name="NDTV"
                ),
                url="https://www.pnas.org/doi/10.1073/pnas.2318525120",
                urlToImage = "https://www.pnas.org/cms/10.1073/pnas.2318525120/asset/175f586c-2b6f-4659-b26f-42b35aa75710/assets/images/large/pnas.2318525120unfig01.jpg"
            ),
            event ={}
        ) {

        }
    }
}