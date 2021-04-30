<?php

namespace App\Form;

use App\Entity\PropertySearch;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\ChoiceList\ChoiceList;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\OptionsResolver\OptionsResolver;

class TriUserType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('Tritype', ChoiceType::class, array('choices'  => array(
            'Par Nom_Asc' => 'Par Nom_Asc',
            'Par Nom_Desc' => 'Par Nom_Desc',
            'Par Prenom_Asc' => 'Par Prenom_Asc',
            'Par Prenom_Desc' => 'Par Prenom_Desc',
            'Par Genre_Asc' => 'Par Genre_Asc',
            'Par Genre_Desc' => 'Par Genre_Desc',
        )

        ));
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => PropertySearch::class,
        ]);
    }
}
